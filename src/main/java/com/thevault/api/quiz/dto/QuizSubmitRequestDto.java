package com.thevault.api.quiz.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuizSubmitRequestDto {

    // Body
    private String height;
    private String weight;
    private String bodyType;
    private String bodyShape;
    private String skinToneGroup;

    // Style preferences
    private List<String> fashionApproaches;
    private List<String> stylePersonas;
    private List<String> colorPalettes;
    private List<String> fabricPreferences;
    private List<String> patternPreferences;
    private List<String> silhouettePreferences;
    private List<String> emphasisAreas;

    // Sizes
    private String sizeTop;
    private String sizeBottom;
    private String sizeDress;
    private String sizeShoes;

    // Budget
    private Integer budgetTopsBottoms;
    private Integer budgetShoes;
    private Integer budgetAccessories;
    private Integer budgetBags;

    // Style Swipe
    private List<Long> swipeLikedImageIds;
    private List<Long> swipeDislikedImageIds;

    // Brands
    private List<String> brandSlugs;

    // Media & social
    private String wardrobePhotoUrl;
    private String instagramUrl;
    private String pinterestUrl;
}
