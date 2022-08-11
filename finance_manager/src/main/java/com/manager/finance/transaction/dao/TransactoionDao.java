package com.manager.finance.transaction.dao;

import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.wallet.model.entity.Wallet;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactoionDao extends JpaRepository<Transaction, Long> {

    List<Transaction> findTransactionsByWallet(Wallet wallet);

}
