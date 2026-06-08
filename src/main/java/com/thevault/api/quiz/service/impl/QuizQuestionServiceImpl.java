package com.thevault.api.quiz.service.impl;

import com.thevault.api.quiz.dto.QuizQuestionDto;
import com.thevault.api.quiz.mapper.QuizMapper;
import com.thevault.api.quiz.repository.StyleQuizQuestionRepository;
import com.thevault.api.quiz.service.QuizQuestionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuizQuestionServiceImpl implements QuizQuestionService {

    private final StyleQuizQuestionRepository questionRepository;
    private final QuizMapper quizMapper;

    @Override
    public List<QuizQuestionDto> getAllQuestions() {
        return quizMapper.toDtoList(questionRepository.findAllByOrderByDisplayOrderAsc());
    }
}
