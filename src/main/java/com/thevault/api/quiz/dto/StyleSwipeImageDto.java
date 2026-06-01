package com.thevault.api.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class StyleSwipeImageDto {

    private Long id;
    private String imageUrl;
    private List<String> tags;
}
