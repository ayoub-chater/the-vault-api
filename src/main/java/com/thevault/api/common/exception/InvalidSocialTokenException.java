package com.thevault.api.common.exception;

public class InvalidSocialTokenException extends RuntimeException {

    public InvalidSocialTokenException() {
        super("Social token is invalid or could not be verified.");
    }
}
