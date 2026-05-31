package com.thevault.api.common.exception;

public class EmailNotVerifiedException extends RuntimeException {

    public EmailNotVerifiedException() {
        super("Email address has not been verified. Please check your inbox for the verification code.");
    }
}
