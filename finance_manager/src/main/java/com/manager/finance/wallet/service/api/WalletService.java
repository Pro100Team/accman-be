package com.manager.finance.wallet.service.api;

import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.wallet.model.entity.Wallet;
import java.util.List;

public interface WalletService {
    List<Wallet> getAll(Profile profile);

    Wallet getById(Long id, Profile profile);

    Long save(Wallet wallet, Profile profile);

    Wallet update(Wallet wallet, Profile profile);

    void delete(Long id, Profile profile);

    Wallet getDefaultWallet(Profile profile);
}
