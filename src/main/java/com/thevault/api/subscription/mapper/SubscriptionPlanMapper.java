package com.thevault.api.subscription.mapper;

import com.thevault.api.subscription.dto.SubscriptionPlanDto;
import com.thevault.api.subscription.entity.SubscriptionPlan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SubscriptionPlanMapper {

    SubscriptionPlanDto toDto(SubscriptionPlan entity);

    List<SubscriptionPlanDto> toDtoList(List<SubscriptionPlan> entities);
}
