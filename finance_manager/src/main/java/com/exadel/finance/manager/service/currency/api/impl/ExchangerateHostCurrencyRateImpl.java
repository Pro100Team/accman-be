package com.exadel.finance.manager.service.currency.api.impl;

import com.exadel.finance.manager.config.currency.api.CurrencyExchangerateFeignClient;
import com.exadel.finance.manager.config.currency.list.Currency;
import com.exadel.finance.manager.model.dto.response.CurrencyRateDto;
import com.exadel.finance.manager.service.CurrencyRateApi;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ExchangerateHostCurrencyRateImpl implements CurrencyRateApi {
    private final CurrencyExchangerateFeignClient currencyApi;

    @Override
    public CurrencyRateDto getAllRatesForBaseCurrency(Currency baseCurrency) {
        log.debug("getAllRatesForBaseCurrency() is called for currency: " + baseCurrency);
        return currencyApi.getAllRatesForBaseCurrency(baseCurrency.value());
    }

    @Override
    public CurrencyRateDto getRateBetweenCurrencyPair(Currency from, Currency to, int amount) {
        log.debug("getRateBetweenCurrencyPair() is called for currency "
                + "FROM: {}, TO: {}, AMOUNT: {} ", from, to, amount);
        return currencyApi.getRateBetweenCurrencyPair(from.value(), to.value(), amount);
    }
}
