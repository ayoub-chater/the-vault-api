package com.thevault.api.auth.service.impl;

import com.thevault.api.auth.dto.JwtResponse;
import com.thevault.api.auth.dto.LoginRequest;
import com.thevault.api.auth.dto.RefreshTokenRequest;
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
import com.thevault.api.common.exception.UserNotFoundException;
import com.thevault.api.user.entity.User;
import com.thevault.api.user.mapper.UserMapper;
import com.thevault.api.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.SecureRandom;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final OtpVerificationRepository otpVerificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

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
    public JwtResponse verifyEmail(VerifyEmailRequest request) {
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

        return buildJwtResponse(user);
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
    public JwtResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException(request.getEmail()));

        if (!user.isEmailVerified()) {
            throw new EmailNotVerifiedException();
        }

        return buildJwtResponse(user);
    }

    @Override
    @Transactional
    public JwtResponse refresh(RefreshTokenRequest request) {
        RefreshToken refreshToken = refreshTokenService.validateAndGet(request.getRefreshToken());

        User user = userRepository.findById(refreshToken.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String newAccessToken = jwtService.generateAccessToken(userDetails);

        return new JwtResponse(newAccessToken, request.getRefreshToken());
    }

    @Override
    @Transactional
    public void logout(RefreshTokenRequest request) {
        refreshTokenService.revokeToken(request.getRefreshToken());
    }

    private JwtResponse buildJwtResponse(User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        String accessToken = jwtService.generateAccessToken(userDetails);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user.getId());
        return new JwtResponse(accessToken, refreshToken.getToken());
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
