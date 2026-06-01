package com.thevault.api.quiz.service;

import com.thevault.api.quiz.dto.QuizQuestionDto;

import java.util.List;

public interface QuizQuestionService {

    List<QuizQuestionDto> getAllQuestions();
}
