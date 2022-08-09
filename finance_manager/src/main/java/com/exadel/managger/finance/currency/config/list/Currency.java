package com.exadel.managger.finance.currency.config.list;

public enum Currency {
    PLN("PLN"),
    USD("USD"),
    EUR("EUR"),
    GEL("GEL"),
    BYR("BYR"),
    GBP("GBP"),
    UAH("UAH");

    private final String value;

    Currency(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
