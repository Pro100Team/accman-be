package com.exadel.finance.manager.currency.service;

import com.exadel.finance.manager.currency.config.list.Currency;
import com.exadel.finance.manager.currency.config.api.CurrencyExchangerateFeignClient;
import com.exadel.finance.manager.currency.model.dto.CurrencyRateDto;
import com.exadel.finance.manager.currency.service.api.CurrencyRateApi;
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
