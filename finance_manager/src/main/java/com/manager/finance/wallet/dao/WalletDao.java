package com.manager.finance.wallet.dao;

import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.wallet.model.entity.Wallet;
import com.manager.finance.wallet.model.entity.api.DefaultCurrency;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<Wallet, Long> {
    List<Wallet> findWalletByProfileIdAndIsDeleted(Profile profileId, Boolean isDeleted, Sort sort);

    Wallet findWalletByIsDefaultAndProfileId(boolean isDefault, Profile profileId);

    Optional<Wallet> findWalletByIdAndProfileIdAndIsDeleted(Long id, Profile profileId,
                                                            boolean isDeleted);

    List<Wallet> findWalletByNameAndCurrencyAndProfileId(String name, DefaultCurrency currency,
                                                         Profile profileId);
}
