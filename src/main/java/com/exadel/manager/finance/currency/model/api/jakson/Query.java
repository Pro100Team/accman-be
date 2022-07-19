package com.exadel.manager.finance.currency.model.api.jakson;

import lombok.Data;

@Data
public class Query {
    private String from;
    private String to;
    private float amount;
}
