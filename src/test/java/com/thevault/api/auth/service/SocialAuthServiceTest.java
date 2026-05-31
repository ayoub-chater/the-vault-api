package com.thevault.api.auth.service;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.SocialAuthRequest;
import com.thevault.api.auth.dto.SocialUserInfo;
import com.thevault.api.auth.entity.RefreshToken;
import com.thevault.api.auth.entity.SocialProvider;
import com.thevault.api.auth.enums.SocialProviderType;
import com.thevault.api.auth.repository.SocialProviderRepository;
import com.thevault.api.auth.service.impl.SocialAuthServiceImpl;
import com.thevault.api.auth.service.impl.SocialTokenValidatorFactory;
import com.thevault.api.config.JwtProperties;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SocialAuthServiceTest {

    @Mock private SocialTokenValidatorFactory validatorFactory;
    @Mock private SocialProviderRepository socialProviderRepository;
    @Mock private UserRepository userRepository;
    @Mock private JwtService jwtService;
    @Mock private RefreshTokenService refreshTokenService;
    @Mock private UserDetailsService userDetailsService;
    @Mock private JwtProperties jwtProperties;
    @Mock private HttpServletResponse httpServletResponse;

    @InjectMocks
    private SocialAuthServiceImpl socialAuthService;

    @Test
    void shouldAuthenticateExistingSocialUser() {
        SocialAuthRequest request = new SocialAuthRequest();
        request.setProvider("GOOGLE");
        request.setToken("google-token");

        SocialUserInfo userInfo = new SocialUserInfo("ayoub@thevault.com", "google-sub-123", "Ayoub", "Chater");
        User user = User.builder().id(1L).email("ayoub@thevault.com").build();
        SocialProvider existingLink = SocialProvider.builder().userId(1L).build();

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder().username("ayoub@thevault.com").password("").authorities(List.of()).build();

        RefreshToken refreshToken = RefreshToken.builder()
                .token("refresh-uuid")
                .expiresAt(LocalDateTime.now().plusDays(7))
                .build();

        SocialTokenValidator mockValidator = mock(SocialTokenValidator.class);
        when(validatorFactory.get(SocialProviderType.GOOGLE)).thenReturn(mockValidator);
        when(mockValidator.validate("google-token")).thenReturn(userInfo);
        when(socialProviderRepository.findByProviderAndProviderId(SocialProviderType.GOOGLE, "google-sub-123"))
                .thenReturn(Optional.of(existingLink));
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(jwtService.generateAccessToken(userDetails)).thenReturn("access-token");
        when(refreshTokenService.createRefreshToken(1L)).thenReturn(refreshToken);
        when(jwtProperties.getRefreshExpirationMs()).thenReturn(604800000L);

        JwtResponse response = socialAuthService.authenticate(request, httpServletResponse);

        assertThat(response.getAccessToken()).isEqualTo("access-token");
        // Refresh token is now in HTTP-only cookie — verify cookie was set
        verify(httpServletResponse).addHeader(anyString(), anyString());
        verify(userRepository, never()).save(any());
    }

    @Test
    void shouldCreateNewUserOnFirstSocialLogin() {
        SocialAuthRequest request = new SocialAuthRequest();
        request.setProvider("GOOGLE");
        request.setToken("google-token");

        SocialUserInfo userInfo = new SocialUserInfo("new@thevault.com", "google-sub-999", "New", "User");
        User savedUser = User.builder().id(2L).email("new@thevault.com").build();

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder().username("new@thevault.com").password("").authorities(List.of()).build();

        RefreshToken refreshToken = RefreshToken.builder()
                .token("refresh-uuid-2")
                .expiresAt(LocalDateTime.now().plusDays(7))
                .build();

        SocialTokenValidator mockValidator = mock(SocialTokenValidator.class);
        when(validatorFactory.get(SocialProviderType.GOOGLE)).thenReturn(mockValidator);
        when(mockValidator.validate("google-token")).thenReturn(userInfo);
        when(socialProviderRepository.findByProviderAndProviderId(any(), anyString())).thenReturn(Optional.empty());
        when(userRepository.findByEmail("new@thevault.com")).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(savedUser);
        when(userDetailsService.loadUserByUsername(anyString())).thenReturn(userDetails);
        when(jwtService.generateAccessToken(userDetails)).thenReturn("access-token-2");
        when(refreshTokenService.createRefreshToken(2L)).thenReturn(refreshToken);
        when(jwtProperties.getRefreshExpirationMs()).thenReturn(604800000L);

        JwtResponse response = socialAuthService.authenticate(request, httpServletResponse);

        assertThat(response.getAccessToken()).isEqualTo("access-token-2");
        verify(userRepository).save(any(User.class));
        verify(socialProviderRepository).save(any(SocialProvider.class));
        verify(httpServletResponse).addHeader(anyString(), anyString());
    }
}
