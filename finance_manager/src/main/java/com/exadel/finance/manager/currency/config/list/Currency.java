package com.exadel.finance.manager.currency.config.list;

public enum Currency {
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    BYR("BYR"),
    AED("AED"),
    PLN("PLN"),
    UAH("UAH");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
