package com.thevault.api.consultant.dto;

import lombok.Data;

@Data
public class ConsultantPortfolioPhotoDto {

    private Long id;
    private String imageUrl;
    private Integer displayOrder;
}
