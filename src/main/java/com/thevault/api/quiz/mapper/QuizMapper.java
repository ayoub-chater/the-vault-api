package com.thevault.api.quiz.mapper;

import com.thevault.api.quiz.dto.QuizOptionDto;
import com.thevault.api.quiz.dto.QuizQuestionDto;
import com.thevault.api.quiz.dto.StyleSwipeImageDto;
import com.thevault.api.quiz.entity.StyleQuizOption;
import com.thevault.api.quiz.entity.StyleQuizQuestion;
import com.thevault.api.quiz.entity.StyleSwipeImage;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuizMapper {

    QuizQuestionDto toDto(StyleQuizQuestion entity);

    QuizOptionDto toOptionDto(StyleQuizOption entity);

    List<QuizQuestionDto> toDtoList(List<StyleQuizQuestion> entities);

    StyleSwipeImageDto toSwipeDto(StyleSwipeImage entity);

    List<StyleSwipeImageDto> toSwipeDtoList(List<StyleSwipeImage> entities);
}
