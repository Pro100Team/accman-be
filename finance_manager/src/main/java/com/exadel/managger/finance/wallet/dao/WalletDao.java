package com.exadel.managger.finance.wallet.dao;

import com.exadel.managger.finance.user.model.entity.User;
import com.exadel.managger.finance.wallet.model.entity.Wallet;
import com.exadel.managger.finance.wallet.model.entity.api.DefaultCurrency;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<Wallet, Long> {
    List<Wallet> findWalletByUserId(User userId);

    Wallet findWalletByIsDefaultAndUserId(boolean isDefault, User userId);

    Optional<Wallet> findWalletByIdAndUserId(Long id, User userId);

    List<Wallet> findWalletByNameAndCurrency(String name, DefaultCurrency currency);
}
