package com.thevault.api.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SocialAuthRequest {

    @NotBlank(message = "Provider is required (GOOGLE, FACEBOOK or APPLE)")
    private String provider;

    @NotBlank(message = "Token is required")
    private String token;
}
