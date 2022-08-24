package com.manager.finance.transaction.dao;

import com.manager.finance.category.model.entity.Category;
import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.wallet.model.entity.Wallet;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TransactionDao extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByWallet(Wallet wallet, Pageable pageable);

    @Query(value = "SELECT * "
                    + "FROM transactions t "
                    + "JOIN wallets w on t.tr_wallet_id = w.w_id "
                    + "WHERE w.w_profile_id = ? ORDER BY t.tr_id DESC --#pageable\\n",
            nativeQuery = true)
    List<Transaction> findAllByProfile(Profile profile);

    List<Transaction> findAllByCategory(Category category);
}
