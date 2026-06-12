package com.thevault.api.common.exception;

public class ZebraSessionNotFoundException extends RuntimeException {

    public ZebraSessionNotFoundException(Long id) {
        super("ZEBRA session not found: " + id);
    }
}
