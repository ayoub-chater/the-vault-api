package com.thevault.api.consultant.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "consultant_portfolio_photos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConsultantPortfolioPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "consultant_id")
    private Long consultantId;

    @Column(name = "image_url")
    private String imageUrl;

    @Column(name = "display_order")
    private Integer displayOrder;
}
