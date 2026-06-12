package com.thevault.api.consultant.dto;

import lombok.Data;

import java.util.List;

@Data
public class ImageConsultantDto {

    private Long id;
    private String displayName;
    private String avatarUrl;
    private String bio;
    private List<String> specialties;
    private boolean available;
    private List<ConsultantPortfolioPhotoDto> portfolio;
}
