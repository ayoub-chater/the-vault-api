package com.thevault.api.auth.service.impl;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.SocialAuthRequest;
import com.thevault.api.auth.dto.SocialUserInfo;
import com.thevault.api.auth.entity.RefreshToken;
import com.thevault.api.auth.entity.SocialProvider;
import com.thevault.api.auth.enums.SocialProviderType;
import com.thevault.api.auth.repository.SocialProviderRepository;
import com.thevault.api.auth.service.JwtService;
import com.thevault.api.auth.service.RefreshTokenService;
import com.thevault.api.auth.service.SocialAuthService;
import com.thevault.api.auth.service.SocialTokenValidator;
import com.thevault.api.common.exception.UnsupportedSocialProviderException;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class SocialAuthServiceImpl implements SocialAuthService {

    private final SocialTokenValidatorFactory validatorFactory;
    private final SocialProviderRepository socialProviderRepository;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserDetailsService userDetailsService;

    @Override
    @Transactional
    public JwtResponse authenticate(SocialAuthRequest request) {
        SocialProviderType providerType = parseProvider(request.getProvider());
        SocialTokenValidator validator = validatorFactory.get(providerType);
        SocialUserInfo userInfo = validator.validate(request.getToken());

        User user = socialProviderRepository
                .findByProviderAndProviderId(providerType, userInfo.getProviderId())
                .map(sp -> userRepository.findById(sp.getUserId()).orElseThrow())
                .orElseGet(() -> findOrCreateUser(userInfo, providerType));

        return buildJwtResponse(user);
    }

    private User findOrCreateUser(SocialUserInfo userInfo, SocialProviderType providerType) {
        User user = userRepository.findByEmail(userInfo.getEmail())
                .orElseGet(() -> createUser(userInfo));

        linkProvider(user, providerType, userInfo.getProviderId());
        return user;
    }

    private User createUser(SocialUserInfo userInfo) {
        User user = User.builder()
                .email(userInfo.getEmail())
                .firstName(userInfo.getFirstName())
                .lastName(userInfo.getLastName())
                .isEmailVerified(true)
                .build();
        return userRepository.save(user);
    }

    private void linkProvider(User user, SocialProviderType providerType, String providerId) {
        SocialProvider link = SocialProvider.builder()
                .userId(user.getId())
                .provider(providerType)
                .providerId(providerId)
                .build();
        socialProviderRepository.save(link);
    }

    private JwtResponse buildJwtResponse(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String accessToken = jwtService.generateAccessToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return new JwtResponse(accessToken, refreshToken.getToken());
    }

    private SocialProviderType parseProvider(String provider) {
        try {
            return SocialProviderType.valueOf(provider.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new UnsupportedSocialProviderException(null);
        }
    }
}
