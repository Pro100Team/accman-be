package com.exadel.finance.manager.wallet;

import java.math.BigDecimal;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {
    private final WalletRepository repository;

    @PostConstruct
    public void injectWallets() {
        Wallet wallet = new Wallet();
        wallet.setName("wallet#" + (1 + (int) (500 * Math.random())));
        wallet.setAmount(BigDecimal.valueOf(1234));
        repository.save(wallet);

        wallet.setId(null);
        wallet.setName("wallet#" + (1 + (int) (500 * Math.random())));
        repository.save(wallet);

        wallet.setId(null);
        wallet.setName("wallet#" + (1 + (int) (500 * Math.random())));
        repository.save(wallet);

    }
}
