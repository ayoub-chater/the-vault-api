package com.thevault.api.quiz.service;

import com.thevault.api.quiz.dto.QuizQuestionDto;
import com.thevault.api.quiz.entity.StyleQuizQuestion;
import com.thevault.api.quiz.mapper.QuizMapper;
import com.thevault.api.quiz.repository.StyleQuizQuestionRepository;
import com.thevault.api.quiz.service.impl.QuizQuestionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class QuizQuestionServiceTest {

    @Mock private StyleQuizQuestionRepository questionRepository;
    @Mock private QuizMapper quizMapper;

    @InjectMocks
    private QuizQuestionServiceImpl quizQuestionService;

    @Test
    void shouldReturnAllQuestionsOrdered() {
        List<StyleQuizQuestion> entities = List.of(
                StyleQuizQuestion.builder().id(1L).questionNumber(1).section("SECTION_1").build(),
                StyleQuizQuestion.builder().id(2L).questionNumber(2).section("SECTION_1").build()
        );

        QuizQuestionDto dto1 = new QuizQuestionDto();
        dto1.setQuestionNumber(1);
        QuizQuestionDto dto2 = new QuizQuestionDto();
        dto2.setQuestionNumber(2);

        when(questionRepository.findAllByOrderByDisplayOrderAsc()).thenReturn(entities);
        when(quizMapper.toDtoList(entities)).thenReturn(List.of(dto1, dto2));

        List<QuizQuestionDto> result = quizQuestionService.getAllQuestions();

        assertThat(result).hasSize(2);
        assertThat(result.get(0).getQuestionNumber()).isEqualTo(1);
    }
}
