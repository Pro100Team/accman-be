package com.exadel.finance.manager.repository;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.model.Wallet;
import java.util.List;

public interface WalletRepository extends SpecificationRepository<Wallet, Long> {
    List<Wallet> findAllByUser(User user);
    Wallet findByUserId (Long user_id);
}
