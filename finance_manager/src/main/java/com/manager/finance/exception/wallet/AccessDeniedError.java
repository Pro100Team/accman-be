package com.manager.finance.exception.wallet;

public class AccessDeniedError extends RuntimeException {
    public AccessDeniedError(String message) {
        super(message);
    }
}
