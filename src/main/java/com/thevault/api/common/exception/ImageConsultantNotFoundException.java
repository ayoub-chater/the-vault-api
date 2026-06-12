package com.thevault.api.common.exception;

public class ImageConsultantNotFoundException extends RuntimeException {

    public ImageConsultantNotFoundException(Long id) {
        super("Image consultant not found: " + id);
    }
}
