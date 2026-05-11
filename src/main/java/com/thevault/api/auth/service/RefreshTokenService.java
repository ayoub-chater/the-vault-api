package com.thevault.api.auth.service;

import com.thevault.api.auth.entity.RefreshToken;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(Long userId);

    RefreshToken validateAndGet(String token);

    void revokeToken(String token);

    void revokeAllForUser(Long userId);
}
