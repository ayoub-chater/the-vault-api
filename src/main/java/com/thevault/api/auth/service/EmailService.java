package com.thevault.api.auth.service;

public interface EmailService {

    void sendOtp(String toEmail, String otp);
}
