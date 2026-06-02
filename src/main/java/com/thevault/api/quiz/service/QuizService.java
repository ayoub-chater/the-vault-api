package com.thevault.api.quiz.service;

import com.thevault.api.quiz.dto.QuizSubmitRequestDto;
import com.thevault.api.quiz.dto.StyleProfileDto;

public interface QuizService {

    StyleProfileDto submit(QuizSubmitRequestDto request);

    StyleProfileDto getMyProfile();

    StyleProfileDto update(QuizSubmitRequestDto request);

    void checkQuizCompleted(Long userId);

    boolean isQuizCompleted(Long userId);
}
