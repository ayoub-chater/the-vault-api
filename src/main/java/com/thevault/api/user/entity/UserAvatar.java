package com.thevault.api.user.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "user_avatars")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserAvatar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "style_config", columnDefinition = "jsonb")
    private Map<String, Object> styleConfig;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "hair_config", columnDefinition = "jsonb")
    private Map<String, Object> hairConfig;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "makeup_config", columnDefinition = "jsonb")
    private Map<String, Object> makeupConfig;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "face_config", columnDefinition = "jsonb")
    private Map<String, Object> faceConfig;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "body_config", columnDefinition = "jsonb")
    private Map<String, Object> bodyConfig;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
