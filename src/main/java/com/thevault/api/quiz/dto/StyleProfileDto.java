package com.thevault.api.quiz.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class StyleProfileDto {

    private Long id;
    private Long userId;
    private String height;
    private String weight;
    private String bodyType;
    private String bodyShape;
    private String skinToneGroup;
    private List<String> fashionApproaches;
    private List<String> stylePersonas;
    private List<String> colorPalettes;
    private List<String> fabricPreferences;
    private List<String> patternPreferences;
    private List<String> silhouettePreferences;
    private List<String> emphasisAreas;
    private String sizeTop;
    private String sizeBottom;
    private String sizeDress;
    private String sizeShoes;
    private Integer budgetTopsBottoms;
    private Integer budgetShoes;
    private Integer budgetAccessories;
    private Integer budgetBags;
    private List<Long> swipeLikedImageIds;
    private List<Long> swipeDislikedImageIds;
    private List<String> brandSlugs;
    private String wardrobePhotoUrl;
    private String instagramUrl;
    private String pinterestUrl;
    private boolean completed;
    private LocalDateTime completedAt;
}
