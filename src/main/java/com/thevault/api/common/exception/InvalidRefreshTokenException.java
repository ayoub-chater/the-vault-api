package com.thevault.api.common.exception;

public class InvalidRefreshTokenException extends RuntimeException {

    public InvalidRefreshTokenException() {
        super("Refresh token is invalid or has expired.");
    }
}
