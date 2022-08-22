package com.manager.finance.exception.wallet;

public class TheSameWalletException extends IllegalArgumentException {

    public TheSameWalletException(String message) {
        super(message);
    }
}
