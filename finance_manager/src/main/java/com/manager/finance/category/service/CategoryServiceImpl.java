/**
 * @Autor GapSerg
 * @Create 2022-08-19.08.2022 23:05
 **/

package com.manager.finance.category.service;

import com.manager.finance.category.dao.CategoryDao;
import com.manager.finance.category.service.api.CategoryService;
import com.manager.finance.mapstruct.mapper.CategoryMapper;
import com.sandbox.model.CategoryRequestDto;
import com.sandbox.model.CategoryResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDao categoryDao;

    private final CategoryMapper categoryMapper;

    @Override
    public List<CategoryResponseDto> getAllExpense() {
        return null;
    }

    @Override
    public List<CategoryResponseDto> getAllIncome() {
        return null;
    }

    @Override
    public CategoryResponseDto getById(Long id) {
        return null;
    }

    @Override
    public CategoryResponseDto update(Long categoryId, CategoryRequestDto categoryRequestDto) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Long save(CategoryRequestDto categoryRequestDto) {

        return categoryDao.save(categoryMapper.toEntity(categoryRequestDto)).getId();
    }
}
