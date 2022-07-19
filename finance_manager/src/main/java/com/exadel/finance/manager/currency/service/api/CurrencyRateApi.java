package com.exadel.finance.manager.currency.service.api;

import com.exadel.finance.manager.currency.config.list.Currency;
import com.exadel.finance.manager.currency.model.dto.CurrencyRateDto;

public interface CurrencyRateApi {
    CurrencyRateDto getAllRatesForBaseCurrency(Currency baseCurrency);

    CurrencyRateDto getRateBetweenCurrencyPair(Currency from, Currency to, int amount);
}
