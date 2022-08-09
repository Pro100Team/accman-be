package com.manager.finance.wallet.model.entity.api;

public enum DefaultCurrency {

    PLN("PLN"),
    USD("USD"),
    EUR("EUR"),
    GEL("GEL");

    private final String value;

    DefaultCurrency(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
