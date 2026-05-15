package com.thevault.api.auth.service;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.LoginRequest;
import com.thevault.api.auth.dto.RegisterRequest;
import com.thevault.api.auth.dto.ResendOtpRequest;
import com.thevault.api.auth.dto.VerifyEmailRequest;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthService {

    void register(RegisterRequest request);

    JwtResponse verifyEmail(VerifyEmailRequest request, HttpServletResponse response);

    void resendOtp(ResendOtpRequest request);

    JwtResponse login(LoginRequest request, HttpServletResponse response);

    JwtResponse refresh(HttpServletRequest request);

    void logout(HttpServletRequest request, HttpServletResponse response);
}
