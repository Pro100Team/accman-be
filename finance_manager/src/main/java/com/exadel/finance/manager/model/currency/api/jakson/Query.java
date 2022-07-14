package com.exadel.finance.manager.model.currency.api.jakson;

import lombok.Data;

@Data
public class Query {
    private String from;
    private String to;
    private float amount;
}
