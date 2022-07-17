package com.exadel.finance.manager.service;

import com.exadel.finance.manager.config.currency.list.Currency;
import com.exadel.finance.manager.model.dto.response.CurrencyRateDto;

public interface CurrencyRateApi {
    CurrencyRateDto getAllRatesForBaseCurrency(Currency baseCurrency);

    CurrencyRateDto getRateBetweenCurrencyPair(Currency from, Currency to, int amount);
}
