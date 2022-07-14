package com.exadel.finance.manager.service.currency.api.impl;

import static com.exadel.finance.manager.config.currency.list.Currency.BYR;
import static com.exadel.finance.manager.config.currency.list.Currency.EUR;
import static com.exadel.finance.manager.config.currency.list.Currency.USD;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
@RequiredArgsConstructor
class ExchangerateHostCurrencyRateImplTest {
    @Autowired private ExchangerateHostCurrencyRateImpl currencyApi;

    @Test
    void getRateBetweenCurrencyPair_givenValidData_thenSuccess() {
        assertNotNull(currencyApi.getRateBetweenCurrencyPair(USD, EUR, 1));
        assertFalse(currencyApi.getRateBetweenCurrencyPair(USD, EUR, 1)
                .getResult().contains("null"));
    }

    @Test
    void getRateBetweenCurrencyPair_givenWrongAmount_thenFail() {
        assertEquals("0", currencyApi.getRateBetweenCurrencyPair(BYR, EUR, 0).getResult());
    }
}
