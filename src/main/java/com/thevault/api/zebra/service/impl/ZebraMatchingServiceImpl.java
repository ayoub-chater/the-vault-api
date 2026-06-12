package com.thevault.api.zebra.service.impl;

import com.thevault.api.common.exception.NoAvailableConsultantException;
import com.thevault.api.common.exception.QuizNotCompletedException;
import com.thevault.api.common.exception.ZebraSessionNotFoundException;
import com.thevault.api.common.security.CurrentUserProvider;
import com.thevault.api.consultant.entity.ImageConsultant;
import com.thevault.api.consultant.repository.ImageConsultantRepository;
import com.thevault.api.consultant.service.ImageConsultantService;
import com.thevault.api.quiz.dto.QuizSubmitRequestDto;
import com.thevault.api.quiz.dto.StyleProfileDto;
import com.thevault.api.quiz.entity.StyleProfile;
import com.thevault.api.quiz.repository.StyleProfileRepository;
import com.thevault.api.quiz.service.QuizService;
import com.thevault.api.user.entity.User;
import com.thevault.api.zebra.dto.ZebraMatchResultDto;
import com.thevault.api.zebra.dto.ZebraSessionDto;
import com.thevault.api.zebra.entity.ZebraSession;
import com.thevault.api.zebra.enums.ZebraSessionStatus;
import com.thevault.api.zebra.mapper.ZebraSessionMapper;
import com.thevault.api.zebra.repository.ZebraSessionRepository;
import com.thevault.api.zebra.service.ZebraMatchingService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@AllArgsConstructor
public class ZebraMatchingServiceImpl implements ZebraMatchingService {

    private final ZebraSessionRepository zebraSessionRepository;
    private final ZebraSessionMapper zebraSessionMapper;
    private final StyleProfileRepository styleProfileRepository;
    private final ImageConsultantRepository imageConsultantRepository;
    private final ImageConsultantService imageConsultantService;
    private final QuizService quizService;
    private final CurrentUserProvider currentUserProvider;

    @Override
    @Transactional
    public ZebraSessionDto startSession() {
        User user = currentUserProvider.getCurrentUser();

        return zebraSessionRepository
                .findFirstByUserIdAndStatusOrderByCreatedAtDesc(user.getId(), ZebraSessionStatus.IN_PROGRESS)
                .map(zebraSessionMapper::toDto)
                .orElseGet(() -> {
                    ZebraSession session = ZebraSession.builder()
                            .userId(user.getId())
                            .status(ZebraSessionStatus.IN_PROGRESS)
                            .build();
                    return zebraSessionMapper.toDto(zebraSessionRepository.save(session));
                });
    }

    @Override
    @Transactional
    public ZebraSessionDto submitAnswers(Long sessionId, QuizSubmitRequestDto request) {
        User user = currentUserProvider.getCurrentUser();
        ZebraSession session = getOwnedSession(sessionId, user.getId());

        StyleProfileDto profile = quizService.submit(request);

        session.setStyleProfileRef(profile.getId());
        session.setStatus(ZebraSessionStatus.COMPLETED);
        session.setCompletedAt(LocalDateTime.now());

        return zebraSessionMapper.toDto(zebraSessionRepository.save(session));
    }

    @Override
    @Transactional
    public ZebraMatchResultDto matchConsultant(Long sessionId) {
        User user = currentUserProvider.getCurrentUser();
        ZebraSession session = getOwnedSession(sessionId, user.getId());

        StyleProfile profile = resolveStyleProfile(session, user.getId());
        Set<String> preferences = collectPreferences(profile);

        List<ImageConsultant> candidates = imageConsultantRepository.findAll().stream()
                .filter(ImageConsultant::isAvailable)
                .toList();

        if (candidates.isEmpty()) {
            throw new NoAvailableConsultantException();
        }

        ImageConsultant best = candidates.stream()
                .max(Comparator
                        .comparingInt((ImageConsultant c) -> overlapScore(c, preferences))
                        .thenComparing(Comparator.comparing(ImageConsultant::getId).reversed()))
                .orElseThrow();

        session.setMatchedConsultantId(best.getId());
        session.setStatus(ZebraSessionStatus.MATCHED);
        if (session.getCompletedAt() == null) {
            session.setCompletedAt(LocalDateTime.now());
        }
        zebraSessionRepository.save(session);

        ZebraMatchResultDto result = new ZebraMatchResultDto();
        result.setConsultantId(best.getId());
        result.setConsultant(imageConsultantService.getConsultantById(best.getId()));
        return result;
    }

    private ZebraSession getOwnedSession(Long sessionId, Long userId) {
        ZebraSession session = zebraSessionRepository.findById(sessionId)
                .orElseThrow(() -> new ZebraSessionNotFoundException(sessionId));

        if (!session.getUserId().equals(userId)) {
            throw new ZebraSessionNotFoundException(sessionId);
        }

        return session;
    }

    private StyleProfile resolveStyleProfile(ZebraSession session, Long userId) {
        Long profileId = session.getStyleProfileRef();
        if (profileId != null) {
            return styleProfileRepository.findById(profileId)
                    .orElseThrow(QuizNotCompletedException::new);
        }

        StyleProfile profile = styleProfileRepository.findByUserId(userId)
                .filter(StyleProfile::isCompleted)
                .orElseThrow(QuizNotCompletedException::new);

        session.setStyleProfileRef(profile.getId());
        return profile;
    }

    private Set<String> collectPreferences(StyleProfile profile) {
        Set<String> preferences = new HashSet<>();
        addAll(preferences, profile.getStylePersonas());
        addAll(preferences, profile.getFashionApproaches());
        addAll(preferences, profile.getColorPalettes());
        addAll(preferences, profile.getFabricPreferences());
        addAll(preferences, profile.getPatternPreferences());
        addAll(preferences, profile.getSilhouettePreferences());
        addAll(preferences, profile.getEmphasisAreas());
        return preferences;
    }

    private void addAll(Set<String> target, List<String> values) {
        if (values == null) {
            return;
        }
        for (String value : values) {
            if (value != null) {
                target.add(value.toUpperCase());
            }
        }
    }

    private int overlapScore(ImageConsultant consultant, Set<String> preferences) {
        if (consultant.getSpecialties() == null) {
            return 0;
        }
        int score = 0;
        for (String specialty : consultant.getSpecialties()) {
            if (specialty != null && preferences.contains(specialty.toUpperCase())) {
                score++;
            }
        }
        return score;
    }
}
