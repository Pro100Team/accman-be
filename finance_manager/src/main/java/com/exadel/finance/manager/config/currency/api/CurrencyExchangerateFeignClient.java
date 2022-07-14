package com.exadel.finance.manager.config.currency.api;

import com.exadel.finance.manager.model.dto.CurrencyRateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "exchangerate", url = "${currency-api.exchangerate.url}")
public interface CurrencyExchangerateFeignClient {
    @RequestMapping(method = RequestMethod.GET,
                    value = "/convert?from={from}&to={to}&amount={amount}")
    CurrencyRateDto getRateBetweenCurrencyPair(
            @PathVariable(value = "from") String from,
            @PathVariable(value = "to") String to,
            @RequestParam(value = "amount", required = false, defaultValue = "1") int amount);

    @RequestMapping(method = RequestMethod.GET, value = "/rates/{base}")
    CurrencyRateDto getAllRatesForBaseCurrency(@PathVariable(value = "base") String baseCurrency);
}
