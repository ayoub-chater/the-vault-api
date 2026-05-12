package com.thevault.api.common.exception;

import com.thevault.api.auth.enums.SocialProviderType;

public class UnsupportedSocialProviderException extends RuntimeException {

    public UnsupportedSocialProviderException(SocialProviderType provider) {
        super("Social provider not supported: " + provider);
    }
}
