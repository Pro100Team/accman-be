package com.manager.finance.category.service.api;

import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.ProfileCategory;
import java.util.List;

public interface ProfileCategoryService {
    Long save(CategoryRequestDto categoryRequestDto);

    CategoryResponseDto update(Long categoryId, CategoryRequestDto categoryRequestDto);

    void delete(Long id);

    List<ProfileCategory> findAllIncomeCategory();

    List<ProfileCategory> findAllExpenseCategory();

    ProfileCategory getById(Long parentCategoryId);
}
