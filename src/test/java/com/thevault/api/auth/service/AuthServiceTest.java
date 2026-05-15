package com.thevault.api.auth.service;

import com.thevault.api.auth.dto.RegisterRequest;
import com.thevault.api.auth.entity.OtpVerification;
import com.thevault.api.auth.repository.OtpVerificationRepository;
import com.thevault.api.auth.service.impl.AuthServiceImpl;
import com.thevault.api.common.exception.EmailAlreadyExistsException;
import com.thevault.api.config.JwtProperties;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.mapper.UserMapper;
import com.thevault.api.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock private UserRepository userRepository;
    @Mock private OtpVerificationRepository otpVerificationRepository;
    @Mock private PasswordEncoder passwordEncoder;
    @Mock private EmailService emailService;
    @Mock private JwtService jwtService;
    @Mock private UserDetailsService userDetailsService;
    @Mock private UserMapper userMapper;
    @Mock private AuthenticationManager authenticationManager;
    @Mock private RefreshTokenService refreshTokenService;
    @Mock private JwtProperties jwtProperties;

    @InjectMocks
    private AuthServiceImpl authService;

    @Test
    void shouldThrowWhenEmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("existing@thevault.com");
        request.setPassword("password123");

        when(userRepository.existsByEmail("existing@thevault.com")).thenReturn(true);

        assertThatThrownBy(() -> authService.register(request))
                .isInstanceOf(EmailAlreadyExistsException.class);

        verify(userRepository, never()).save(any());
        verify(emailService, never()).sendOtp(anyString(), anyString());
    }

    @Test
    void shouldSaveUserAndSendOtpOnValidRegistration() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("ayoub@thevault.com");
        request.setPassword("password123");

        User mappedUser = User.builder().email("ayoub@thevault.com").build();

        when(userRepository.existsByEmail(anyString())).thenReturn(false);
        when(userMapper.toEntity(request)).thenReturn(mappedUser);
        when(passwordEncoder.encode(anyString())).thenReturn("hashed_password");
        when(userRepository.save(any(User.class))).thenAnswer(inv -> {
            User u = inv.getArgument(0);
            u.setId(1L);
            return u;
        });

        authService.register(request);

        ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userCaptor.capture());
        assertThat(userCaptor.getValue().getPasswordHash()).isEqualTo("hashed_password");

        verify(otpVerificationRepository).save(any(OtpVerification.class));
        verify(emailService).sendOtp(eq("ayoub@thevault.com"), anyString());
    }
}
