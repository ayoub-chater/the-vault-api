package com.thevault.api.common.exception;

public class NoAvailableConsultantException extends RuntimeException {

    public NoAvailableConsultantException() {
        super("No available image consultants found for matching.");
    }
}
