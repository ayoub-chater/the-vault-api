package com.thevault.api.auth.service.impl;

import com.thevault.api.auth.dto.GoogleUserInfo;
import com.thevault.api.auth.dto.SocialUserInfo;
import com.thevault.api.auth.enums.SocialProviderType;
import com.thevault.api.auth.service.SocialTokenValidator;
import com.thevault.api.common.exception.InvalidSocialTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class GoogleTokenValidator implements SocialTokenValidator {

    private static final String USERINFO_URL = "https://www.googleapis.com/oauth2/v3/userinfo";

    private final RestClient restClient = RestClient.create();

    @Override
    public SocialProviderType supports() {
        return SocialProviderType.GOOGLE;
    }

    @Override
    public SocialUserInfo validate(String token) {
        try {
            GoogleUserInfo info = restClient.get()
                    .uri(USERINFO_URL)
                    .header("Authorization", "Bearer " + token)
                    .retrieve()
                    .body(GoogleUserInfo.class);

            if (info == null || info.getEmail() == null) {
                throw new InvalidSocialTokenException();
            }

            return new SocialUserInfo(
                    info.getEmail(),
                    info.getSub(),
                    info.getGivenName(),
                    info.getFamilyName()
            );
        } catch (InvalidSocialTokenException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InvalidSocialTokenException();
        }
    }
}
