package com.thevault.api.service_catalog.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "service_catalog")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String slug;
    private String name;
    private String tagline;
    private String description;
    private String note;

    @Column(name = "cover_image_url")
    private String coverImageUrl;

    @Column(name = "display_order")
    private Integer displayOrder;
}
