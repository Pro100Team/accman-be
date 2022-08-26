package com.manager.finance.transaction.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sandbox.model.CategoryResponseDto;
import com.sandbox.model.TransactionTypeParameter;

public class TransactionResponseDto {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("transactionType")
    private TransactionTypeParameter transactionType;
    @JsonProperty("date")
    private String date;
    @JsonProperty("category")
    private CategoryResponseDto category;
    @JsonProperty("subcategory")
    private CategoryResponseDto subcategory;
    @JsonProperty("amount")
    private String amount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("walletName")
    private String walletName;
    @JsonProperty("payer")
    private String payer;
    @JsonProperty("notes")
    private String notes;
}
