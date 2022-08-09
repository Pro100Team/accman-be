package com.exadel.finance.manager.currency.controller;

import com.exadel.finance.manager.currency.config.list.Currency;
import com.exadel.finance.manager.currency.model.dto.CurrencyRateDto;
import com.exadel.finance.manager.currency.service.api.CurrencyRateApi;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/rates")
@RequiredArgsConstructor
public class CurrencyApiController {
    private final CurrencyRateApi currencyApi;

    @GetMapping("/{base}")
    public ResponseEntity<CurrencyRateDto> getAllRatesForBase(
            @PathVariable("base") Currency baseCurrency) {
        return ResponseEntity.ok().body(
                currencyApi.getAllRatesForBaseCurrency(baseCurrency));
    }

    @GetMapping("/pair")
    public ResponseEntity<CurrencyRateDto> getRatesBetweenCurrencyPair(
            @RequestParam(name = "from") Currency from,
            @RequestParam(name = "to") Currency to,
            @RequestParam(name = "amount", required = false, defaultValue = "1") int amount) {
        return ResponseEntity.ok().body(
                currencyApi.getRateBetweenCurrencyPair(from, to, amount));
    }
}
