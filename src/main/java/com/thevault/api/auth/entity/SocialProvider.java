package com.thevault.api.auth.entity;

import com.thevault.api.auth.enums.SocialProviderType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "social_providers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SocialProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    private SocialProviderType provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
