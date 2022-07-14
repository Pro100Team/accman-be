package com.exadel.finance.manager.service.currency.api.impl;

import com.exadel.finance.manager.config.currency.api.CurrencyExchangerateFeignClient;
import com.exadel.finance.manager.config.currency.list.Currency;
import com.exadel.finance.manager.model.dto.CurrencyRateDto;
import com.exadel.finance.manager.service.CurrencyRateApi;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExchangerateHostCurrencyRateImpl implements CurrencyRateApi {
    private final CurrencyExchangerateFeignClient currencyApi;

    @Override
    public CurrencyRateDto getAllRatesForBaseCurrency(Currency baseCurrency) {
        try {
            return currencyApi.getAllRatesForBaseCurrency(baseCurrency.value());
        } catch (FeignException ex) {
            throw new RuntimeException(
                    String.format("Could not process currency rates inquiry for %s. "
                    + "Error %s received", baseCurrency.value(), ex.status()));
        }
    }

    @Override
    public CurrencyRateDto getRateBetweenCurrencyPair(Currency from, Currency to, int amount) {
        try {
            return currencyApi.getRateBetweenCurrencyPair(from.value(), to.value(), amount);
        } catch (FeignException ex) {
            throw new RuntimeException(
                    String.format("Could not process currency rates inquiry for %s : %s. "
                                    + "Error %s received", from.value(), to.value(), ex.status()));
        }
    }
}
