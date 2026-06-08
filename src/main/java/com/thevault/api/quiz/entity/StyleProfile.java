package com.thevault.api.quiz.entity;

import com.thevault.api.quiz.enums.BodyShape;
import com.thevault.api.quiz.enums.SkinToneGroup;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "style_profiles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StyleProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    // Body
    private String height;
    private String weight;

    @Column(name = "body_type")
    private String bodyType;

    @Enumerated(EnumType.STRING)
    @Column(name = "body_shape")
    private BodyShape bodyShape;

    @Enumerated(EnumType.STRING)
    @Column(name = "skin_tone_group")
    private SkinToneGroup skinToneGroup;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "fashion_approaches", columnDefinition = "jsonb")
    private List<String> fashionApproaches;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "style_personas", columnDefinition = "jsonb")
    private List<String> stylePersonas;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "color_palettes", columnDefinition = "jsonb")
    private List<String> colorPalettes;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "fabric_preferences", columnDefinition = "jsonb")
    private List<String> fabricPreferences;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "pattern_preferences", columnDefinition = "jsonb")
    private List<String> patternPreferences;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "silhouette_preferences", columnDefinition = "jsonb")
    private List<String> silhouettePreferences;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "emphasis_areas", columnDefinition = "jsonb")
    private List<String> emphasisAreas;

    // Sizes
    @Column(name = "size_top")
    private String sizeTop;

    @Column(name = "size_bottom")
    private String sizeBottom;

    @Column(name = "size_dress")
    private String sizeDress;

    @Column(name = "size_shoes")
    private String sizeShoes;

    // Budget
    @Column(name = "budget_tops_bottoms")
    private Integer budgetTopsBottoms;

    @Column(name = "budget_shoes")
    private Integer budgetShoes;

    @Column(name = "budget_accessories")
    private Integer budgetAccessories;

    @Column(name = "budget_bags")
    private Integer budgetBags;

    // Style Swipe results
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "swipe_liked_image_ids", columnDefinition = "jsonb")
    private List<Long> swipeLikedImageIds;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "swipe_disliked_image_ids", columnDefinition = "jsonb")
    private List<Long> swipeDislikedImageIds;

    // Brand preferences
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "brand_slugs", columnDefinition = "jsonb")
    private List<String> brandSlugs;

    // Media & social
    @Column(name = "wardrobe_photo_url")
    private String wardrobePhotoUrl;

    @Column(name = "instagram_url")
    private String instagramUrl;

    @Column(name = "pinterest_url")
    private String pinterestUrl;

    // Completion
    @Column(name = "is_completed")
    @Builder.Default
    private boolean completed = false;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
