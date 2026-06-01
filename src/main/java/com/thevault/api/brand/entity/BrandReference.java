package com.thevault.api.brand.entity;

import com.thevault.api.brand.enums.BrandTier;
import jakarta.persistence.*;
import lombok.*;

// Shared entity
@Entity
@Table(name = "brand_references")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandReference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String slug;

    @Enumerated(EnumType.STRING)
    private BrandTier tier;

    @Column(name = "logo_url")
    private String logoUrl;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "is_active")
    @Builder.Default
    private boolean active = true;
}
