package com.manager.finance.category.service.api;

import com.manager.finance.category.model.dto.request.SubcategoryRequestDto;
import com.manager.finance.category.model.dto.response.ExpenseSubcategoryResponseDto;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;

public interface ProfileSubcategoryService {
    List<ExpenseSubcategoryResponseDto> findAllByCategoryId(Long categoryId,
                                                            Profile profileId);

    Long save(SubcategoryRequestDto subcategoryRequestDto);

    void delete(Long subcategoryId);
}
