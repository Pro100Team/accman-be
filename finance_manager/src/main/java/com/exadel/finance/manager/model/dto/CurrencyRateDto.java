package com.exadel.finance.manager.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.management.Query;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyRateDto {
    private Boolean success;
    private Query query;
    private Boolean historical;
    private String date;
    private String result;
}
