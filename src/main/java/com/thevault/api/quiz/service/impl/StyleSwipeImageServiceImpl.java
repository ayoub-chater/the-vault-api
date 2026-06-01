package com.thevault.api.quiz.service.impl;

import com.thevault.api.quiz.dto.StyleSwipeImageDto;
import com.thevault.api.quiz.mapper.QuizMapper;
import com.thevault.api.quiz.repository.StyleSwipeImageRepository;
import com.thevault.api.quiz.service.StyleSwipeImageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StyleSwipeImageServiceImpl implements StyleSwipeImageService {

    private final StyleSwipeImageRepository swipeImageRepository;
    private final QuizMapper quizMapper;

    @Override
    public List<StyleSwipeImageDto> getAllActiveImages() {
        return quizMapper.toSwipeDtoList(swipeImageRepository.findAllActive());
    }
}
