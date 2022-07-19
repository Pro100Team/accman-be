package com.exadel.manager.finance.currency.model.dto;

import com.exadel.manager.finance.currency.model.currency.api.jakson.Query;
import com.exadel.manager.finance.currency.model.currency.api.jakson.Rates;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRateDto {
    private Boolean success;
    private Boolean historical;
    private Query query;
    private String base;
    private String date;
    private Float result;
    private Rates rates;
}
