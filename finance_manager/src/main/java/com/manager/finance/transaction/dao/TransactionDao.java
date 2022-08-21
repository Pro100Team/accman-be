package com.manager.finance.transaction.dao;

import com.manager.finance.category.model.entity.Category;
import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.wallet.model.entity.Wallet;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByWallet(Wallet wallet, Pageable pageable);

    List<Transaction> findAllByCategory(Category category);
}
