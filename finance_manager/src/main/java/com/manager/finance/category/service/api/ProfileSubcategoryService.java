package com.manager.finance.category.service.api;

import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.ProfileSubcategory;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;

public interface ProfileSubcategoryService {
    List<CategoryResponseDto> findAllByParentCategoryId(Long categoryId,
                                                        Profile profileId);

    Long save(CategoryRequestDto subcategoryRequestDto, Profile profile);

    void delete(Long subcategoryId);

    CategoryResponseDto update(Long categoryId, CategoryRequestDto categoryRequestDto);

    void createDefaultCategory(Profile profile);

    ProfileSubcategory findByCategoryId(Category category, Profile profile);

}
