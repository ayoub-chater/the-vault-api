package com.thevault.api.auth.service;

import com.thevault.api.auth.dto.LoginRequest;
import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.entity.RefreshToken;
import com.thevault.api.auth.repository.OtpVerificationRepository;
import com.thevault.api.auth.service.impl.AuthServiceImpl;
import com.thevault.api.common.exception.EmailNotVerifiedException;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.mapper.UserMapper;
import com.thevault.api.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginTest {

    @Mock private UserRepository userRepository;
    @Mock private OtpVerificationRepository otpVerificationRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private EmailService emailService;
    @Mock private JwtService jwtService;
    @Mock private UserDetailsService userDetailsService;
    @Mock private UserMapper userMapper;
    @Mock private AuthenticationManager authenticationManager;
    @Mock private RefreshTokenService refreshTokenService;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void shouldReturnTokensOnSuccessfulLogin() {
        LoginRequest request = new LoginRequest();
        request.setEmail("ayoub@thevault.com");
        request.setPassword("password123");

        User user = User.builder()
                .id(1L)
                .email("ayoub@thevault.com")
                .isEmailVerified(true)
                .build();

        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder().username("ayoub@thevault.com").password("hashed").authorities(List.of()).build();

        RefreshToken refreshToken = RefreshToken.builder()
                .token("uuid-refresh-token")
                .expiresAt(LocalDateTime.now().plusDays(7))
                .build();

        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class)))
                .thenReturn(null);
        when(userRepository.findByEmail("ayoub@thevault.com")).thenReturn(Optional.of(user));
        when(userDetailsService.loadUserByUsername("ayoub@thevault.com")).thenReturn(userDetails);
        when(jwtService.generateAccessToken(userDetails)).thenReturn("access-token");
        when(refreshTokenService.createRefreshToken(1L)).thenReturn(refreshToken);

        JwtResponse response = authService.login(request);

        assertThat(response.getAccessToken()).isEqualTo("access-token");
        assertThat(response.getRefreshToken()).isEqualTo("uuid-refresh-token");
    }

    @Test
    void shouldThrowWhenEmailNotVerified() {
        LoginRequest request = new LoginRequest();
        request.setEmail("unverified@thevault.com");
        request.setPassword("password123");

        User user = User.builder()
                .id(1L)
                .email("unverified@thevault.com")
                .isEmailVerified(false)
                .build();

        when(authenticationManager.authenticate(any())).thenReturn(null);
        when(userRepository.findByEmail("unverified@thevault.com")).thenReturn(Optional.of(user));

        assertThatThrownBy(() -> authService.login(request))
                .isInstanceOf(EmailNotVerifiedException.class);
    }
}
