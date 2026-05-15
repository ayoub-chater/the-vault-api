package com.thevault.api.auth.service.impl;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.LoginRequest;
import com.thevault.api.auth.dto.RegisterRequest;
import com.thevault.api.auth.dto.ResendOtpRequest;
import com.thevault.api.auth.dto.VerifyEmailRequest;
import com.thevault.api.auth.entity.OtpVerification;
import com.thevault.api.auth.entity.RefreshToken;
import com.thevault.api.auth.repository.OtpVerificationRepository;
import com.thevault.api.auth.service.AuthService;
import com.thevault.api.auth.service.EmailService;
import com.thevault.api.auth.service.JwtService;
import com.thevault.api.auth.service.RefreshTokenService;
import com.thevault.api.common.exception.EmailAlreadyExistsException;
import com.thevault.api.common.exception.EmailNotVerifiedException;
import com.thevault.api.common.exception.InvalidOtpException;
import com.thevault.api.common.exception.InvalidRefreshTokenException;
import com.thevault.api.common.exception.UserNotFoundException;
import com.thevault.api.config.JwtProperties;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.mapper.UserMapper;
import com.thevault.api.user.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private static final String REFRESH_TOKEN_COOKIE = "refreshToken";

    private final UserRepository userRepository;
    private final OtpVerificationRepository otpVerificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final JwtProperties jwtProperties;

    @Override
    @Transactional
    public void register(RegisterRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }
        User user = userMapper.toEntity(request);
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        userRepository.save(user);
        issueOtp(user);
    }

    @Override
    @Transactional
    public JwtResponse verifyEmail(VerifyEmailRequest request, HttpServletResponse response) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));

        OtpVerification otp = otpVerificationRepository
                .findTopByUserIdAndIsUsedFalseOrderByCreatedAtDesc(user.getId())
                .orElseThrow(InvalidOtpException::new);

        if (otp.isExpired() || !otp.getCode().equals(request.getCode())) {
            throw new InvalidOtpException();
        }

        otp.setUsed(true);
        otpVerificationRepository.save(otp);
        user.setEmailVerified(true);
        userRepository.save(user);

        return buildJwtResponse(user, response);
    }

    @Override
    @Transactional
    public void resendOtp(ResendOtpRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));
        otpVerificationRepository.invalidateAllByUserId(user.getId());
        issueOtp(user);
    }

    @Override
    @Transactional
    public JwtResponse login(LoginRequest request, HttpServletResponse response) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));

        if (!user.isEmailVerified()) {
            throw new EmailNotVerifiedException();
        }
        return buildJwtResponse(user, response);
    }

    @Override
    @Transactional
    public JwtResponse refresh(HttpServletRequest request) {
        String token = extractRefreshTokenFromCookie(request);
        if (token == null) {
            throw new InvalidRefreshTokenException();
        }
        RefreshToken refreshToken = refreshTokenService.validateAndGet(token);
        User user = userRepository.findById(refreshToken.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        return new JwtResponse(jwtService.generateAccessToken(userDetails));
    }

    @Override
    @Transactional
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        String token = extractRefreshTokenFromCookie(request);
        if (token != null) {
            refreshTokenService.revokeToken(token);
        }
        clearRefreshTokenCookie(response);
    }

    // DRY — shared by login and verifyEmail.
    private JwtResponse buildJwtResponse(User user, HttpServletResponse response) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String accessToken = jwtService.generateAccessToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        setRefreshTokenCookie(response, refreshToken.getToken());
        return new JwtResponse(accessToken);
    }

    // HTTP-only + SameSite=Strict prevents XSS and CSRF.
    // secure(false) for local dev — set to true in production (HTTPS only).
    private void setRefreshTokenCookie(HttpServletResponse response, String token) {
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN_COOKIE, token)
                .httpOnly(true)
                .secure(false)
                .path("/api/auth")
                .maxAge(Duration.ofMillis(jwtProperties.getRefreshExpirationMs()))
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    private void clearRefreshTokenCookie(HttpServletResponse response) {
        ResponseCookie cookie = ResponseCookie.from(REFRESH_TOKEN_COOKIE, "")
                .httpOnly(true)
                .secure(false)
                .path("/api/auth")
                .maxAge(0)
                .sameSite("Strict")
                .build();
        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());
    }

    private String extractRefreshTokenFromCookie(HttpServletRequest request) {
        if (request.getCookies() == null) {
            return null;
        }
        return Arrays.stream(request.getCookies())
                .filter(c -> REFRESH_TOKEN_COOKIE.equals(c.getName()))
                .map(Cookie::getValue)
                .findFirst()
                .orElse(null);
    }

    private void issueOtp(User user) {
        String code = generateOtpCode();
        OtpVerification otp = OtpVerification.builder()
                .userId(user.getId())
                .code(code)
                .expiresAt(LocalDateTime.now().plusMinutes(10))
                .build();
        otpVerificationRepository.save(otp);
        emailService.sendOtp(user.getEmail(), code);
    }

    private String generateOtpCode() {
        return String.format("%04d", new SecureRandom().nextInt(10000));
    }
}
