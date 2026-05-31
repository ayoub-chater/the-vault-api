package com.thevault.api.auth.service.impl;

import com.thevault.api.auth.service.EmailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendOtp(String toEmail, String otp) {
        log.info("OTP for {}: {}", toEmail, otp);
    }
}
