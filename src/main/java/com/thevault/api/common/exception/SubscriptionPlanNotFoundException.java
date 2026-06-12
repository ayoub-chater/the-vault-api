package com.thevault.api.common.exception;

public class SubscriptionPlanNotFoundException extends RuntimeException {

    public SubscriptionPlanNotFoundException(Long id) {
        super("Subscription plan not found: " + id);
    }
}
