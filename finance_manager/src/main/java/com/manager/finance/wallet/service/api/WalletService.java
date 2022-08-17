package com.manager.finance.wallet.service.api;

import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.wallet.model.entity.Wallet;
import java.util.List;

public interface WalletService {
    List<Wallet> getAll();

    Wallet getByIdWithUserHolder(Long id);

    Long save(Wallet wallet);

    Wallet update(Wallet wallet);

    void delete(Long id);

    Wallet getDefaultWallet(Profile profile);
}
