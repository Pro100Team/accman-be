package com.exadel.finance.manager.wallet.dao;

import com.exadel.finance.manager.wallet.model.entity.User;
import com.exadel.finance.manager.wallet.model.entity.Wallet;
import com.exadel.finance.manager.wallet.model.entity.api.DefaultCurrency;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<Wallet, Long> {
    List<Wallet> findWalletByUserId(User userId);

    Wallet findWalletByIsDefault(boolean b);

    List<Wallet> findWalletByNameAndCurrency(String name, DefaultCurrency currency);
}
