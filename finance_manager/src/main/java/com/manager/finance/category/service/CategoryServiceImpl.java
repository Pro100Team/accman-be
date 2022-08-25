/**
 * @Autor GapSerg
 * @Create 2022-08-19.08.2022 23:05
 **/

package com.manager.finance.category.service;

import com.manager.finance.category.dao.CategoryDao;
import com.manager.finance.category.exception.CategoryNotFoundException;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.service.api.CategoryService;
import com.manager.finance.mapstruct.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    private final CategoryMapper categoryMapper;

    @Override
    public Category getById(Long id) {
        return categoryDao.findById(id).orElseThrow(
                () -> new CategoryNotFoundException("Category with id: " + id + " not found"));
    }

    @Override
    public Category getByName(String name) {
        return categoryDao.findByName(name);
    }

    @Override
    public Category save(String name) {
        return categoryDao.save(Category.builder().name(name).isDefault(false).build());
    }
}
