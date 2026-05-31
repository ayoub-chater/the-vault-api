package com.thevault.api.common.exception;

public class InvalidOtpException extends RuntimeException {

    public InvalidOtpException() {
        super("Invalid or expired OTP code");
    }
}
