package com.exadel.finance.manager.model.dto.response;

import com.exadel.finance.manager.config.currency.list.Currency;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class WalletResponseDto {
    private Long id;
    private String name;
    private BigDecimal amount;
    private Currency currency;
    private Boolean isDefault;
}
