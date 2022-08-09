package com.manager.finance.currency.service.api;

import com.manager.finance.currency.config.list.Currency;
import com.manager.finance.currency.model.dto.CurrencyRateDto;

public interface CurrencyRateApi {
    CurrencyRateDto getAllRatesForBaseCurrency(Currency baseCurrency);

    CurrencyRateDto getRateBetweenCurrencyPair(Currency from, Currency to, int amount);
}
