package com.thevault.api.auth.service;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.SocialAuthRequest;

public interface SocialAuthService {

    JwtResponse authenticate(SocialAuthRequest request);
}
