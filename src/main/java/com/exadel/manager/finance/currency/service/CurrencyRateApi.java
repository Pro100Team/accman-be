package com.exadel.manager.finance.currency.service;

import com.exadel.manager.finance.currency.list.Currency;
import com.exadel.manager.finance.currency.model.dto.CurrencyRateDto;

public interface CurrencyRateApi {
    CurrencyRateDto getAllRatesForBaseCurrency(Currency baseCurrency);

    CurrencyRateDto getRateBetweenCurrencyPair(Currency from, Currency to, int amount);
}
