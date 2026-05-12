package com.thevault.api.auth.service.impl;

import com.thevault.api.auth.enums.SocialProviderType;
import com.thevault.api.auth.service.SocialTokenValidator;
import com.thevault.api.common.exception.UnsupportedSocialProviderException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class SocialTokenValidatorFactory {

    private final Map<SocialProviderType, SocialTokenValidator> validators;

    public SocialTokenValidatorFactory(List<SocialTokenValidator> validators) {
        this.validators = validators.stream()
                .collect(Collectors.toMap(SocialTokenValidator::supports, v -> v));
    }

    public SocialTokenValidator get(SocialProviderType provider) {
        SocialTokenValidator validator = validators.get(provider);
        if (validator == null) {
            throw new UnsupportedSocialProviderException(provider);
        }
        return validator;
    }
}
