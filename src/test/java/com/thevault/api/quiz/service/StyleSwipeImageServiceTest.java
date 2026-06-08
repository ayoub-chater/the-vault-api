package com.thevault.api.quiz.service;

import com.thevault.api.quiz.dto.StyleSwipeImageDto;
import com.thevault.api.quiz.entity.StyleSwipeImage;
import com.thevault.api.quiz.mapper.QuizMapper;
import com.thevault.api.quiz.repository.StyleSwipeImageRepository;
import com.thevault.api.quiz.service.impl.StyleSwipeImageServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StyleSwipeImageServiceTest {

    @Mock private StyleSwipeImageRepository swipeImageRepository;
    @Mock private QuizMapper quizMapper;

    @InjectMocks
    private StyleSwipeImageServiceImpl swipeImageService;

    @Test
    void shouldReturnAllActiveImages() {
        List<StyleSwipeImage> entities = List.of(
                StyleSwipeImage.builder().id(1L).imageUrl("/images/quiz/swipe/swipe-01.jpg").build(),
                StyleSwipeImage.builder().id(2L).imageUrl("/images/quiz/swipe/swipe-02.jpg").build()
        );

        StyleSwipeImageDto dto1 = new StyleSwipeImageDto();
        dto1.setId(1L);
        StyleSwipeImageDto dto2 = new StyleSwipeImageDto();
        dto2.setId(2L);

        when(swipeImageRepository.findAllActive()).thenReturn(entities);
        when(quizMapper.toSwipeDtoList(entities)).thenReturn(List.of(dto1, dto2));

        List<StyleSwipeImageDto> result = swipeImageService.getAllActiveImages();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getId()).isEqualTo(1L);
    }
}
