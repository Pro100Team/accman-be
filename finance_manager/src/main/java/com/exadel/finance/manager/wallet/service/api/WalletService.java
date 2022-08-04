package com.exadel.finance.manager.wallet.service.api;

import com.exadel.finance.manager.wallet.model.entity.Wallet;
import com.sandbox.model.WalletRequestDto;
import java.util.List;

public interface WalletService {
    List<Wallet> getAll();

    Wallet get(Long id);

    Long save(Wallet wallet);

    Wallet update(Wallet wallet, Long id);

    void delete(Long id);
}
