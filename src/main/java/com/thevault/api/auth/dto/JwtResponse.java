package com.thevault.api.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtResponse {

    private String accessToken;
    private String refreshToken;
    private final String tokenType = "Bearer";
}
