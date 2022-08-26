package com.manager.finance.category.dao;

import com.manager.finance.category.model.entity.DefaultSubcategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaultSubcategoryDao extends JpaRepository<DefaultSubcategory, Long> {
}
