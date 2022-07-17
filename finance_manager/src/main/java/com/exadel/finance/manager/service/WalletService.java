package com.exadel.finance.manager.service;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.model.Wallet;
import java.util.List;

public interface WalletService {
    Wallet saveOrUpdate(Wallet wallet);

    Wallet findById(Long id);

    List<Wallet> findAllByUser(User user);

    void deleteById(Long id);

    Wallet setUniqueDefaultWallet(Wallet wallet);
}
