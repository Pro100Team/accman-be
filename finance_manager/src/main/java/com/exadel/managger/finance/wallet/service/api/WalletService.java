package com.exadel.managger.finance.wallet.service.api;

import com.exadel.managger.finance.wallet.model.entity.Wallet;
import java.util.List;

public interface WalletService {
    List<Wallet> getAll();

    Wallet get(Long id);

    Long save(Wallet wallet);

    Wallet update(Wallet wallet, Long id);

    void delete(Long id);
}
