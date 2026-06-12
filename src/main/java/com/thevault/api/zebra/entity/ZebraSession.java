package com.thevault.api.zebra.entity;

import com.thevault.api.zebra.enums.ZebraSessionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "zebra_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ZebraSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ZebraSessionStatus status = ZebraSessionStatus.IN_PROGRESS;

    @Column(name = "matched_consultant_id")
    private Long matchedConsultantId;

    @Column(name = "style_profile_ref")
    private Long styleProfileRef;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "completed_at")
    private LocalDateTime completedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
