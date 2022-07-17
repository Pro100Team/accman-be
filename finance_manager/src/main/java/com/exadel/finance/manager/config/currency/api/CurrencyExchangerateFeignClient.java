package com.exadel.finance.manager.config.currency.api;

import com.exadel.finance.manager.model.dto.CurrencyRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchangerate", url = "${currency-api.exchangerate.url}")
public interface CurrencyExchangerateFeignClient {
    @RequestMapping(method = RequestMethod.GET, value = "/convert")
    CurrencyRateDto getRateBetweenCurrencyPair(
            @RequestParam(value = "from") String from,
            @RequestParam(value = "to") String to,
            @RequestParam(value = "amount", required = false, defaultValue = "1") int amount);

    @RequestMapping(method = RequestMethod.GET, value = "/latest")
    CurrencyRateDto getAllRatesForBaseCurrency(
            @RequestParam(value = "base", required = false, defaultValue = "USD") String base);
}
