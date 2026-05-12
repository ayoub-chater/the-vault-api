package com.thevault.api.service_catalog.dto;

import lombok.Data;

@Data
public class ServiceCatalogDto {

    private Long id;
    private String slug;
    private String name;
    private String tagline;
    private String description;
    private String note;
    private String coverImageUrl;
    private Integer displayOrder;
}
