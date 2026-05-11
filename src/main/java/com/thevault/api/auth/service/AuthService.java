package com.thevault.api.auth.service;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.RegisterRequest;
import com.thevault.api.auth.dto.ResendOtpRequest;
import com.thevault.api.auth.dto.VerifyEmailRequest;


public interface AuthService {

    void register(RegisterRequest request);

    JwtResponse verifyEmail(VerifyEmailRequest request);

    void resendOtp(ResendOtpRequest request);
}
