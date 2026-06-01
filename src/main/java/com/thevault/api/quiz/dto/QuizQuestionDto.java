package com.thevault.api.quiz.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class QuizQuestionDto {

    private Long id;
    private Integer questionNumber;
    private String section;
    private String questionText;
    private String questionType;
    private String placeholderText;
    private boolean required;
    private Map<String, Object> metadata;
    private List<QuizOptionDto> options;
}
