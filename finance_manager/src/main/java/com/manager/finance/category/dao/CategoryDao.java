package com.manager.finance.category.dao;

import com.manager.finance.category.model.entity.Category;
import com.manager.finance.user.model.entity.Profile;
import com.sandbox.model.TransactionTypeParameter;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {
    List<Category> findAllByCategoryTypeAndProfile(
            TransactionTypeParameter categoryType, Profile profile);

    Optional<Category> findByIdAndProfile(Long id, Profile profile);
}
