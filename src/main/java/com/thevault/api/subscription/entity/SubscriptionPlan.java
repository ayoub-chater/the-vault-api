package com.thevault.api.subscription.entity;

import com.thevault.api.subscription.enums.SubscriptionTier;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "subscription_plans")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubscriptionPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private SubscriptionTier tier;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "monthly_price")
    private BigDecimal monthlyPrice;

    @Column(name = "yearly_price")
    private BigDecimal yearlyPrice;

    @Builder.Default
    private String currency = "GBP";

    @Column(name = "consultation_minutes")
    private Integer consultationMinutes;

    @Column(name = "look_board_limit")
    private Integer lookBoardLimit;

    @Column(name = "chat_days")
    private Integer chatDays;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> features;

    @Column(name = "is_popular")
    @Builder.Default
    private boolean popular = false;

    @Column(name = "display_order")
    private Integer displayOrder;

    @Column(name = "is_active")
    @Builder.Default
    private boolean active = true;
}
