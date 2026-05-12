package com.thevault.api.auth.service.impl;

import com.thevault.api.auth.dto.SocialUserInfo;
import com.thevault.api.auth.enums.SocialProviderType;
import com.thevault.api.auth.service.SocialTokenValidator;
import org.springframework.stereotype.Component;

@Component
public class AppleTokenValidator implements SocialTokenValidator {

    @Override
    public SocialProviderType supports() {
        return SocialProviderType.APPLE;
    }

    @Override
    public SocialUserInfo validate(String token) {
        throw new UnsupportedOperationException("Apple Sign-In is not yet implemented. Coming in V2.");
    }
}
