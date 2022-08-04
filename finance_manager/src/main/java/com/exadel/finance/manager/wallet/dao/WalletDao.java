package com.exadel.finance.manager.wallet.dao;

import com.exadel.finance.manager.wallet.model.entity.Wallet;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletDao extends JpaRepository<Wallet, Long> {
    Optional<List<Wallet>> findWalletByUserId(Long id);
}
