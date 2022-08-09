package com.exadel.finance.manager.user.model.entity.api;

public enum Country {
    POLAND("Poland"),
    UKRAINE("Ukraine"),
    BELARUS("Belarus"),
    LITHUANIA("Lithuania ");
    private final String value;

    Country(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
