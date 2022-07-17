package com.exadel.finance.manager.repository;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.model.Wallet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Long> {
    List<Wallet> findAllByUser(User user);
}
