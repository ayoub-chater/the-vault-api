package com.thevault.api.auth.controller;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.LoginRequest;
import com.thevault.api.auth.dto.MessageResponse;
import com.thevault.api.auth.dto.RegisterRequest;
import com.thevault.api.auth.dto.ResendOtpRequest;
import com.thevault.api.auth.dto.SocialAuthRequest;
import com.thevault.api.auth.dto.VerifyEmailRequest;
import com.thevault.api.auth.service.AuthService;
import com.thevault.api.auth.service.SocialAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "User registration, login, and token management")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final SocialAuthService socialAuthService;

    @Operation(summary = "Register a new user")
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Account created. Please check your email for the verification code."));
    }

    @Operation(summary = "Verify email with OTP")
    @PostMapping("/verify-email")
    public ResponseEntity<JwtResponse> verifyEmail(
            @Valid @RequestBody VerifyEmailRequest request,
            HttpServletResponse response) {
        return ResponseEntity.ok(authService.verifyEmail(request, response));
    }

    @Operation(summary = "Resend OTP code")
    @PostMapping("/resend-otp")
    public ResponseEntity<MessageResponse> resendOtp(@Valid @RequestBody ResendOtpRequest request) {
        authService.resendOtp(request);
        return ResponseEntity.ok(new MessageResponse("A new verification code has been sent to your email."));
    }

    @Operation(summary = "Login with email and password")
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(
            @Valid @RequestBody LoginRequest request,
            HttpServletResponse response) {
        return ResponseEntity.ok(authService.login(request, response));
    }

    @Operation(summary = "Social login (Google, Facebook, Apple)")
    @PostMapping("/social")
    public ResponseEntity<JwtResponse> social(
            @Valid @RequestBody SocialAuthRequest request,
            HttpServletResponse response) {
        return ResponseEntity.ok(socialAuthService.authenticate(request, response));
    }

    @Operation(summary = "Refresh access token", description = "Reads refresh token from HTTP-only cookie")
    @PostMapping("/refresh")
    public ResponseEntity<JwtResponse> refresh(HttpServletRequest request) {
        return ResponseEntity.ok(authService.refresh(request));
    }

    @Operation(summary = "Logout", description = "Revokes the refresh token cookie")
    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(
            HttpServletRequest request,
            HttpServletResponse response) {
        authService.logout(request, response);
        return ResponseEntity.ok(new MessageResponse("Successfully logged out."));
    }
}
