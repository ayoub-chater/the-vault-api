package com.thevault.api.subscription.dto;

import com.thevault.api.subscription.enums.SubscriptionTier;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class SubscriptionPlanDto {

    private Long id;
    private SubscriptionTier tier;
    private String displayName;
    private BigDecimal monthlyPrice;
    private BigDecimal yearlyPrice;
    private String currency;
    private Integer consultationMinutes;
    private Integer lookBoardLimit;
    private Integer chatDays;
    private List<String> features;
    private boolean popular;
    private Integer displayOrder;
}
