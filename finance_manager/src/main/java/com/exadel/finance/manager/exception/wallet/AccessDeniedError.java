package com.exadel.finance.manager.exception.wallet;

public class AccessDeniedError extends RuntimeException {
    public AccessDeniedError(String message) {
        super(message);
    }
}
