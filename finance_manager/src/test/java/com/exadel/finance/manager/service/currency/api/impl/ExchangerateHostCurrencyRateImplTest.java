package com.exadel.finance.manager.service.currency.api.impl;

import static com.exadel.finance.manager.config.currency.list.Currency.AED;
import static com.exadel.finance.manager.config.currency.list.Currency.EUR;
import static com.exadel.finance.manager.config.currency.list.Currency.USD;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.exadel.finance.manager.config.AbstractTest;
import com.exadel.finance.manager.model.dto.response.CurrencyRateDto;
import feign.FeignException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWireMock(port = 9091)
class ExchangerateHostCurrencyRateImplTest extends AbstractTest {
    @Autowired private ExchangerateHostCurrencyRateImpl currencyApi;

    @Test
    public void getRateBetweenCurrencyPair_givenValidData_thenSuccess() throws IOException {
        stubFor(get(urlEqualTo("/convert?from=USD&to=EUR&amount=1"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/usd-eur"))));

        CurrencyRateDto currencyMock = this.currencyApi.getRateBetweenCurrencyPair(USD, EUR, 1);
        assertTrue(currencyMock.getSuccess());
        assertFalse(currencyMock.getHistorical());
        assertEquals("USD", currencyMock.getQuery().getFrom());
        assertEquals("EUR", currencyMock.getQuery().getTo());
        assertEquals(1.0, currencyMock.getQuery().getAmount());
        assertEquals("2022-07-15", currencyMock.getDate());
        assertEquals(0.997226f, currencyMock.getResult());
    }

    @Test
    public void getAllRatesForBaseCurrency_givenValidData_thenSuccess() throws IOException {
        stubFor(get(urlEqualTo("/latest?base=EUR"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody(read("stubs/all-rates"))));

        CurrencyRateDto currencyMock = this.currencyApi.getAllRatesForBaseCurrency(EUR);
        assertTrue(currencyMock.getSuccess());
        assertEquals("EUR", currencyMock.getBase());
        assertEquals("2022-06-13", currencyMock.getDate());
        assertEquals(3.851564f, currencyMock.getRates().getAed());
        assertEquals(94.018951f, currencyMock.getRates().getAfn());
        assertEquals(120.16758f, currencyMock.getRates().getAll());
        assertEquals(452.141883f, currencyMock.getRates().getAmd());
        assertEquals(1.908799f, currencyMock.getRates().getAng());
    }

    @Test
    public void getAllRatesForBaseCurrency_givenWrongLink_thenFail() {
        stubFor(get(urlEqualTo("/latest?SOMETHING_WRONG"))
                .willReturn(aResponse().withStatus(HttpStatus.NOT_FOUND.value())));
        assertThrows(FeignException.NotFound.class,
                () -> currencyApi.getAllRatesForBaseCurrency(AED),
                "Expected FeignException.NotFound when link is wrong");
    }

    private String read(String location) throws IOException {
        return IOUtils.toString(
                new ClassPathResource(location).getInputStream(), StandardCharsets.UTF_8.name());
    }
}
