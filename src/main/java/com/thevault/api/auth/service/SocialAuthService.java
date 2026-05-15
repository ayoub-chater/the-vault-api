package com.thevault.api.auth.service;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.SocialAuthRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface SocialAuthService {

    JwtResponse authenticate(SocialAuthRequest request, HttpServletResponse response);
}
