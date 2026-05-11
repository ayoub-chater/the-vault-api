package com.thevault.api.auth.service.impl;

import com.thevault.api.auth.entity.RefreshToken;
import com.thevault.api.auth.repository.RefreshTokenRepository;
import com.thevault.api.auth.service.RefreshTokenService;
import com.thevault.api.common.exception.InvalidRefreshTokenException;
import com.thevault.api.config.JwtProperties;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;
    private final JwtProperties jwtProperties;

    @Override
    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken token = RefreshToken.builder()
                .userId(userId)
                .token(UUID.randomUUID().toString())
                .expiresAt(LocalDateTime.now().plusSeconds(
                        jwtProperties.getRefreshExpirationMs() / 1000
                ))
                .build();

        return refreshTokenRepository.save(token);
    }

    @Override
    public RefreshToken validateAndGet(String token) {
        return refreshTokenRepository.findByToken(token)
                .filter(t -> !t.isRevoked() && !t.isExpired())
                .orElseThrow(InvalidRefreshTokenException::new);
    }

    @Override
    public void revokeToken(String token) {
        refreshTokenRepository.findByToken(token).ifPresent(t -> {
            t.setRevoked(true);
            refreshTokenRepository.save(t);
        });
    }

    @Override
    public void revokeAllForUser(Long userId) {
        refreshTokenRepository.revokeAllByUserId(userId);
    }
}
