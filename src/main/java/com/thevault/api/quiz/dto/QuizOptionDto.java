package com.thevault.api.quiz.dto;

import lombok.Data;

@Data
public class QuizOptionDto {

    private Long id;
    private String optionText;
    private String optionValue;
    private String imageUrl;
}
