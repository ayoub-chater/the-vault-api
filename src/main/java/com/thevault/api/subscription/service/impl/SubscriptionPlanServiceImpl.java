package com.thevault.api.subscription.service.impl;

import com.thevault.api.common.exception.SubscriptionPlanNotFoundException;
import com.thevault.api.subscription.dto.SubscriptionPlanDto;
import com.thevault.api.subscription.mapper.SubscriptionPlanMapper;
import com.thevault.api.subscription.repository.SubscriptionPlanRepository;
import com.thevault.api.subscription.service.SubscriptionPlanService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SubscriptionPlanServiceImpl implements SubscriptionPlanService {

    private final SubscriptionPlanRepository subscriptionPlanRepository;
    private final SubscriptionPlanMapper subscriptionPlanMapper;

    @Override
    public List<SubscriptionPlanDto> getAllPlans() {
        return subscriptionPlanMapper.toDtoList(
                subscriptionPlanRepository.findAllByActiveTrueOrderByDisplayOrderAsc()
        );
    }

    @Override
    public SubscriptionPlanDto getPlanById(Long id) {
        return subscriptionPlanRepository.findById(id)
                .map(subscriptionPlanMapper::toDto)
                .orElseThrow(() -> new SubscriptionPlanNotFoundException(id));
    }
}
