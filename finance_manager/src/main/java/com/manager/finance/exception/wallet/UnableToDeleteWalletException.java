package com.manager.finance.exception.wallet;

public class UnableToDeleteWalletException extends IllegalArgumentException {
    public UnableToDeleteWalletException(String message) {
        super(message);
    }
}
