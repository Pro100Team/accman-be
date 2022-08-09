package com.manager.finance.currency.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.manager.finance.currency.model.api.jakson.Query;
import com.manager.finance.currency.model.api.jakson.Rates;
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
