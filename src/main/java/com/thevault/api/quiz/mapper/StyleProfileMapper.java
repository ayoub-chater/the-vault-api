package com.thevault.api.quiz.mapper;

import com.thevault.api.quiz.dto.QuizSubmitRequestDto;
import com.thevault.api.quiz.dto.StyleProfileDto;
import com.thevault.api.quiz.entity.StyleProfile;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StyleProfileMapper {

    StyleProfileDto toDto(StyleProfile entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateFromRequest(QuizSubmitRequestDto request, @MappingTarget StyleProfile entity);
}
