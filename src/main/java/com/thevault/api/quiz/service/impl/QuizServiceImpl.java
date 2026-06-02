package com.thevault.api.quiz.service.impl;

import com.thevault.api.common.exception.QuizNotCompletedException;
import com.thevault.api.common.exception.UserNotFoundException;
import com.thevault.api.common.security.CurrentUserProvider;
import com.thevault.api.quiz.dto.QuizSubmitRequestDto;
import com.thevault.api.quiz.dto.StyleProfileDto;
import com.thevault.api.quiz.entity.StyleProfile;
import com.thevault.api.quiz.mapper.StyleProfileMapper;
import com.thevault.api.quiz.repository.StyleProfileRepository;
import com.thevault.api.quiz.service.QuizService;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class QuizServiceImpl implements QuizService {

    private static final int QUIZ_COMPLETION_KEYS = 30;

    private final StyleProfileRepository styleProfileRepository;
    private final StyleProfileMapper styleProfileMapper;
    private final CurrentUserProvider currentUserProvider;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public StyleProfileDto submit(QuizSubmitRequestDto request) {
        User user = currentUserProvider.getCurrentUser();

        StyleProfile profile = styleProfileRepository
                .findByUserId(user.getId())
                .orElseGet(() -> StyleProfile.builder().userId(user.getId()).build());

        styleProfileMapper.updateFromRequest(request, profile);

        if (!profile.isCompleted()) {
            profile.setCompleted(true);
            profile.setCompletedAt(LocalDateTime.now());
            user.setKeyBalance(user.getKeyBalance() + QUIZ_COMPLETION_KEYS);
            userRepository.save(user);
        }

        return styleProfileMapper.toDto(styleProfileRepository.save(profile));
    }

    @Override
    public StyleProfileDto getMyProfile() {
        User user = currentUserProvider.getCurrentUser();
        StyleProfile profile = styleProfileRepository
                .findByUserId(user.getId())
                .orElseThrow(() -> new UserNotFoundException(user.getEmail()));
        return styleProfileMapper.toDto(profile);
    }

    @Override
    @Transactional
    public StyleProfileDto update(QuizSubmitRequestDto request) {
        User user = currentUserProvider.getCurrentUser();
        StyleProfile profile = styleProfileRepository
                .findByUserId(user.getId())
                .orElseGet(() -> StyleProfile.builder().userId(user.getId()).build());

        styleProfileMapper.updateFromRequest(request, profile);
        return styleProfileMapper.toDto(styleProfileRepository.save(profile));
    }

    @Override
    public void checkQuizCompleted(Long userId) {
        if (!styleProfileRepository.existsByUserIdAndCompletedTrue(userId)) {
            throw new QuizNotCompletedException();
        }
    }

    @Override
    public boolean isQuizCompleted(Long userId) {
        return styleProfileRepository.existsByUserIdAndCompletedTrue(userId);
    }
}
