package com.manager.finance.category.dao;

import com.manager.finance.category.model.entity.Category;
import com.manager.finance.transaction.model.entity.Transaction;
import com.manager.finance.wallet.model.entity.Wallet;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {

}
