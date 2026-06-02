package com.thevault.api.quiz.service;

import com.thevault.api.common.exception.QuizNotCompletedException;
import com.thevault.api.common.exception.UserNotFoundException;
import com.thevault.api.common.security.CurrentUserProvider;
import com.thevault.api.quiz.dto.QuizSubmitRequestDto;
import com.thevault.api.quiz.dto.StyleProfileDto;
import com.thevault.api.quiz.entity.StyleProfile;
import com.thevault.api.quiz.mapper.StyleProfileMapper;
import com.thevault.api.quiz.repository.StyleProfileRepository;
import com.thevault.api.quiz.service.impl.QuizServiceImpl;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

    @Mock private StyleProfileRepository styleProfileRepository;
    @Mock private StyleProfileMapper styleProfileMapper;
    @Mock private CurrentUserProvider currentUserProvider;
    @Mock private UserRepository userRepository;

    @InjectMocks
    private QuizServiceImpl quizService;

    private User user;
    private StyleProfile profile;
    private StyleProfileDto profileDto;
    private QuizSubmitRequestDto request;

    @BeforeEach
    void setUp() {
        user = User.builder().id(1L).email("user@test.com").keyBalance(0).build();

        profile = StyleProfile.builder()
                .id(10L)
                .userId(1L)
                .completed(false)
                .build();

        profileDto = new StyleProfileDto();
        profileDto.setCompleted(true);

        request = new QuizSubmitRequestDto();
    }

    // submit

    @Test
    void submit_shouldCreateNewProfileWhenNoneExists() {
        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(styleProfileRepository.findByUserId(1L)).thenReturn(Optional.empty());
        when(styleProfileRepository.save(any(StyleProfile.class))).thenReturn(profile);
        when(styleProfileMapper.toDto(profile)).thenReturn(profileDto);

        StyleProfileDto result = quizService.submit(request);

        assertThat(result.isCompleted()).isTrue();
        verify(styleProfileRepository).save(any(StyleProfile.class));
    }

    @Test
    void submit_shouldReuseExistingProfileWhenPresent() {
        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(styleProfileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));
        when(styleProfileRepository.save(profile)).thenReturn(profile);
        when(styleProfileMapper.toDto(profile)).thenReturn(profileDto);

        quizService.submit(request);

        verify(styleProfileMapper).updateFromRequest(request, profile);
    }

    @Test
    void submit_shouldMarkProfileAsCompletedOnFirstSubmission() {
        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(styleProfileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));
        when(styleProfileRepository.save(profile)).thenReturn(profile);
        when(styleProfileMapper.toDto(profile)).thenReturn(profileDto);

        quizService.submit(request);

        assertThat(profile.isCompleted()).isTrue();
        assertThat(profile.getCompletedAt()).isNotNull();
    }

    @Test
    void submit_shouldAward30KeysOnFirstCompletion() {
        user = User.builder().id(1L).email("user@test.com").keyBalance(10).build();

        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(styleProfileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));
        when(styleProfileRepository.save(profile)).thenReturn(profile);
        when(styleProfileMapper.toDto(profile)).thenReturn(profileDto);

        quizService.submit(request);

        assertThat(user.getKeyBalance()).isEqualTo(40);
        verify(userRepository).save(user);
    }

    @Test
    void submit_shouldNotAwardKeysIfAlreadyCompleted() {
        profile.setCompleted(true);
        profile.setCompletedAt(LocalDateTime.of(2025, 1, 1, 10, 0));

        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(styleProfileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));
        when(styleProfileRepository.save(profile)).thenReturn(profile);
        when(styleProfileMapper.toDto(profile)).thenReturn(profileDto);

        quizService.submit(request);

        assertThat(user.getKeyBalance()).isEqualTo(0);
        verify(userRepository, never()).save(any());
    }

    @Test
    void submit_shouldNotOverwriteCompletedAtOnResubmission() {
        LocalDateTime original = LocalDateTime.of(2025, 1, 1, 10, 0);
        profile.setCompleted(true);
        profile.setCompletedAt(original);

        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(styleProfileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));
        when(styleProfileRepository.save(profile)).thenReturn(profile);
        when(styleProfileMapper.toDto(profile)).thenReturn(profileDto);

        quizService.submit(request);

        assertThat(profile.getCompletedAt()).isEqualTo(original);
    }

    // getMyProfile

    @Test
    void getMyProfile_shouldReturnProfileForCurrentUser() {
        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(styleProfileRepository.findByUserId(1L)).thenReturn(Optional.of(profile));
        when(styleProfileMapper.toDto(profile)).thenReturn(profileDto);

        StyleProfileDto result = quizService.getMyProfile();

        assertThat(result).isNotNull();
    }

    @Test
    void getMyProfile_shouldThrowWhenProfileNotFound() {
        when(currentUserProvider.getCurrentUser()).thenReturn(user);
        when(styleProfileRepository.findByUserId(1L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> quizService.getMyProfile())
                .isInstanceOf(UserNotFoundException.class);
    }

    // checkQuizCompleted

    @Test
    void checkQuizCompleted_shouldPassWhenCompleted() {
        when(styleProfileRepository.existsByUserIdAndCompletedTrue(1L)).thenReturn(true);

        quizService.checkQuizCompleted(1L);

        verify(styleProfileRepository).existsByUserIdAndCompletedTrue(1L);
    }

    @Test
    void checkQuizCompleted_shouldThrowWhenNotCompleted() {
        when(styleProfileRepository.existsByUserIdAndCompletedTrue(1L)).thenReturn(false);

        assertThatThrownBy(() -> quizService.checkQuizCompleted(1L))
                .isInstanceOf(QuizNotCompletedException.class)
                .hasMessageContaining("Main Quiz");
    }

    // isQuizCompleted

    @Test
    void isQuizCompleted_shouldReturnTrueWhenCompleted() {
        when(styleProfileRepository.existsByUserIdAndCompletedTrue(1L)).thenReturn(true);

        assertThat(quizService.isQuizCompleted(1L)).isTrue();
    }

    @Test
    void isQuizCompleted_shouldReturnFalseWhenNotCompleted() {
        when(styleProfileRepository.existsByUserIdAndCompletedTrue(1L)).thenReturn(false);

        assertThat(quizService.isQuizCompleted(1L)).isFalse();
    }
}
