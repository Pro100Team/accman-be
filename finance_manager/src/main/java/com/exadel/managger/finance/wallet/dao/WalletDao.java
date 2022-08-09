package com.exadel.managger.finance.wallet.dao;

import com.exadel.managger.finance.user.model.entity.Profile;
import com.exadel.managger.finance.user.model.entity.User;
import com.exadel.managger.finance.wallet.model.entity.Wallet;
import com.exadel.managger.finance.wallet.model.entity.api.DefaultCurrency;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<Wallet, Long> {
    List<Wallet> findWalletByProfileId(Profile profileId);

    Wallet findWalletByIsDefaultAndProfileId(boolean isDefault, Profile profileId);

    Optional<Wallet> findWalletByIdAndProfileId(Long id, Profile profileId);

    List<Wallet> findWalletByNameAndCurrency(String name, DefaultCurrency currency);
}
