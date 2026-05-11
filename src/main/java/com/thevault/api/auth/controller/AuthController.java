package com.thevault.api.auth.controller;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.MessageResponse;
import com.thevault.api.auth.dto.RegisterRequest;
import com.thevault.api.auth.dto.ResendOtpRequest;
import com.thevault.api.auth.dto.VerifyEmailRequest;
import com.thevault.api.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authentication", description = "User registration and email verification")
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @Operation(summary = "Register a new user", description = "Creates a new account and sends a 4-digit OTP to the provided email")
    @PostMapping("/register")
    public ResponseEntity<MessageResponse> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new MessageResponse("Account created. Please check your email for the verification code."));
    }

    @Operation(summary = "Verify email with OTP", description = "Validates the OTP and returns a JWT access token on success")
    @PostMapping("/verify-email")
    public ResponseEntity<JwtResponse> verifyEmail(@Valid @RequestBody VerifyEmailRequest request) {
        return ResponseEntity.ok(authService.verifyEmail(request));
    }

    @Operation(summary = "Resend OTP code", description = "Invalidates the previous OTP and sends a new one to the user's email")
    @PostMapping("/resend-otp")
    public ResponseEntity<MessageResponse> resendOtp(@Valid @RequestBody ResendOtpRequest request) {
        authService.resendOtp(request);
        return ResponseEntity.ok(new MessageResponse("A new verification code has been sent to your email."));
    }
}
