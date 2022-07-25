package com.exadel.finance.manager.model.dto.request;

import com.exadel.finance.manager.config.currency.list.Currency;
import java.math.BigDecimal;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class WalletRequestDto {
    private Long id;
    @NotBlank(message = "Please enter wallet name")
    @Size(min = 3, message = "Name must be at least 3 symbols long")
    private String name;
    private BigDecimal amount;
    private Currency currency;
    private Long userId;
    private Boolean isDefault;
}
