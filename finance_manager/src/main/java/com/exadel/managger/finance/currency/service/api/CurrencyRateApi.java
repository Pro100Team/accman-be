package com.exadel.managger.finance.currency.service.api;

import com.exadel.managger.finance.currency.config.list.Currency;
import com.exadel.managger.finance.currency.model.dto.CurrencyRateDto;

public interface CurrencyRateApi {
    CurrencyRateDto getAllRatesForBaseCurrency(Currency baseCurrency);

    CurrencyRateDto getRateBetweenCurrencyPair(Currency from, Currency to, int amount);
}
