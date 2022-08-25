package com.manager.finance.category.dao;

import com.manager.finance.category.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category, Long> {

    Category findByName(String name);

}
