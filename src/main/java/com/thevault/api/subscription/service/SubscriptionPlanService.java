package com.thevault.api.subscription.service;

import com.thevault.api.subscription.dto.SubscriptionPlanDto;

import java.util.List;

public interface SubscriptionPlanService {

    List<SubscriptionPlanDto> getAllPlans();

    SubscriptionPlanDto getPlanById(Long id);
}
