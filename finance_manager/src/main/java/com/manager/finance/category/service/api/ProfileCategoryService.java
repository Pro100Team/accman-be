package com.manager.finance.category.service.api;

import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;

public interface ProfileCategoryService {
    Long save(CategoryRequestDto categoryRequestDto, Profile profile);

    CategoryResponseDto update(Long categoryId, CategoryRequestDto categoryDto,Profile profile);

    void delete(Long id);

    List<ProfileCategory> findAllIncomeCategory(Profile profile);

    List<ProfileCategory> findAllExpenseCategory(Profile profile);

    ProfileCategory getById(Long parentCategoryId);

    void createDefaultCategory(Profile profile);

    ProfileCategory getByCategoryIdAndProfile(Category parentCategory, Profile profile);
}
