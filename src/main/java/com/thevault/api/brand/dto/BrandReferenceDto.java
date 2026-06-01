package com.thevault.api.brand.dto;

import lombok.Data;

@Data
public class BrandReferenceDto {

    private Long id;
    private String name;
    private String slug;
    private String tier;
    private String logoUrl;
}
