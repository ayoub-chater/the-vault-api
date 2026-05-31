package com.thevault.api.auth.service;

import com.thevault.api.auth.service.impl.JwtServiceImpl;
import com.thevault.api.config.JwtProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class JwtServiceTest {

    private JwtService jwtService;
    private UserDetails userDetails;

    @BeforeEach
    void setUp() {
        JwtProperties properties = new JwtProperties();
        properties.setSecret("vault-pfe-2025-built-with-passion-never-stop");
        properties.setExpirationMs(900000L);
        properties.setRefreshExpirationMs(604800000L);

        jwtService = new JwtServiceImpl(properties);

        userDetails = User.builder()
                .username("ayoub@thevault.com")
                .password("hashed")
                .authorities(List.of())
                .build();
    }

    @Test
    void shouldGenerateValidAccessToken() {
        String token = jwtService.generateAccessToken(userDetails);
        assertThat(token).isNotBlank();
    }

    @Test
    void shouldExtractEmailFromToken() {
        String token = jwtService.generateAccessToken(userDetails);
        assertThat(jwtService.extractEmail(token)).isEqualTo("ayoub@thevault.com");
    }

    @Test
    void shouldValidateTokenForCorrectUser() {
        String token = jwtService.generateAccessToken(userDetails);
        assertThat(jwtService.isTokenValid(token, userDetails)).isTrue();
    }

    @Test
    void shouldInvalidateTokenForWrongUser() {
        String token = jwtService.generateAccessToken(userDetails);

        UserDetails otherUser = User.builder()
                .username("other@thevault.com")
                .password("hashed")
                .authorities(List.of())
                .build();

        assertThat(jwtService.isTokenValid(token, otherUser)).isFalse();
    }
}
