package com.thevault.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

// Refresh token is no longer returned in the response body.
// It is set as an HTTP-only cookie by the server — JavaScript cannot access it.
@Data
@AllArgsConstructor
public class JwtResponse {

    private String accessToken;
    private final String tokenType = "Bearer";
}
