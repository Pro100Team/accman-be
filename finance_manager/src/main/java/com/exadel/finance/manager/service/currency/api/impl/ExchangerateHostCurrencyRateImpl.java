package com.exadel.finance.manager.service.currency.api.impl;

import com.exadel.finance.manager.config.currency.api.CurrencyExchangerateFeignClient;
import com.exadel.finance.manager.config.currency.list.Currency;
import com.exadel.finance.manager.model.dto.CurrencyRateDto;
import com.exadel.finance.manager.service.CurrencyRateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangerateHostCurrencyRateImpl implements CurrencyRateApi {
    private final CurrencyExchangerateFeignClient currencyApi;

    @Override
    public CurrencyRateDto getAllRatesForBaseCurrency(Currency baseCurrency) {
        return currencyApi.getAllRatesForBaseCurrency(baseCurrency.value());
    }

    @Override
    public CurrencyRateDto getRateBetweenCurrencyPair(Currency from, Currency to, int amount) {
        return currencyApi.getRateBetweenCurrencyPair(from.value(), to.value(), amount);
    }
}
