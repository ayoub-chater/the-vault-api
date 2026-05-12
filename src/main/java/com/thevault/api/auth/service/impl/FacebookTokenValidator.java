package com.thevault.api.auth.service.impl;

import com.thevault.api.auth.dto.FacebookUserInfo;
import com.thevault.api.auth.dto.SocialUserInfo;
import com.thevault.api.auth.enums.SocialProviderType;
import com.thevault.api.auth.service.SocialTokenValidator;
import com.thevault.api.common.exception.InvalidSocialTokenException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class FacebookTokenValidator implements SocialTokenValidator {

    private static final String GRAPH_URL =
            "https://graph.facebook.com/me?fields=id,email,first_name,last_name&access_token=";

    private final RestClient restClient = RestClient.create();

    @Override
    public SocialProviderType supports() {
        return SocialProviderType.FACEBOOK;
    }

    @Override
    public SocialUserInfo validate(String token) {
        try {
            FacebookUserInfo info = restClient.get()
                    .uri(GRAPH_URL + token)
                    .retrieve()
                    .body(FacebookUserInfo.class);

            if (info == null || info.getEmail() == null) {
                throw new InvalidSocialTokenException();
            }

            return new SocialUserInfo(
                    info.getEmail(),
                    info.getId(),
                    info.getFirstName(),
                    info.getLastName()
            );
        } catch (InvalidSocialTokenException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new InvalidSocialTokenException();
        }
    }
}
