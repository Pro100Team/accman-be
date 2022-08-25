package com.manager.finance.category.dao;

import com.manager.finance.category.model.entity.DefaultCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DefaultCategoryDao extends JpaRepository<DefaultCategory, Long> {
}
