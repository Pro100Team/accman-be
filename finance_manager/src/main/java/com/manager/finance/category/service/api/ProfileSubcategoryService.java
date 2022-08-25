package com.manager.finance.category.service.api;

import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;

public interface ProfileSubcategoryService {
    List<CategoryResponseDto> findAllByCategoryId(Long categoryId,
                                                  Profile profileId);

    Long save(CategoryRequestDto subcategoryRequestDto);

    void delete(Long subcategoryId);

    CategoryResponseDto update(Long categoryId, CategoryRequestDto categoryRequestDto);
}
