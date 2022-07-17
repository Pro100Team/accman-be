package com.exadel.finance.manager.config.currency.list;

public enum Currency {
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    BYR("BYR"),
    AED("AED"),
    UAH("UAH");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
