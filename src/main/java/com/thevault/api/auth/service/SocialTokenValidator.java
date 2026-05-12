package com.thevault.api.auth.service;

import com.thevault.api.auth.dto.SocialUserInfo;
import com.thevault.api.auth.enums.SocialProviderType;

public interface SocialTokenValidator {

    SocialProviderType supports();

    SocialUserInfo validate(String token);
}
