package com.manager.finance.category.service;

import com.manager.finance.category.dao.SubcategoryDao;
import com.manager.finance.category.exception.CategoryNotFoundException;
import com.manager.finance.category.model.dto.request.SubcategoryRequestDto;
import com.manager.finance.category.model.dto.response.ExpenseSubcategoryResponseDto;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.ProfileSubcategory;
import com.manager.finance.category.service.api.CategoryService;
import com.manager.finance.category.service.api.ProfileSubcategoryService;
import com.manager.finance.mapstruct.mapper.CategoryMapper;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.service.api.ProfileService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileSubcategoryServiceImpl implements ProfileSubcategoryService {
    private final SubcategoryDao subcategoryDao;
    private final CategoryMapper categoryMapper;
    private final ProfileService profileService;
    private final CategoryService categoryService;

    @Override
    public List<ExpenseSubcategoryResponseDto> findAllByCategoryId(Long categoryId,
                                                                   Profile profileId) {
        List<ProfileSubcategory> profileSubCategory =
                subcategoryDao.findByParentCategoryIdAndProfileIdAndIsDeleted(categoryId, profileId,
                        false);
        return categoryMapper.toExpenseSubcategoryResponseDto(profileSubCategory);
    }

    @Override
    public Long save(SubcategoryRequestDto subcategoryRequestDto) {
        Profile profile = profileService.findByUserId();
        if (profile == null) {
            profile = profileService.createDefaultProfile();
        }
        Category category = categoryService.getByName(subcategoryRequestDto.getName());
        if (category == null) {
            category = categoryService.save(subcategoryRequestDto.getName());
        }

        ProfileSubcategory profileSubcategory =
                categoryMapper.toProfileSubcategory(subcategoryRequestDto, category, profile);
        profileSubcategory.setIsDeleted(false);
        ProfileSubcategory savedCategory = subcategoryDao.save(profileSubcategory);
        return savedCategory.getId();
    }

    @Override
    public void delete(Long subcategoryId) {
        ProfileSubcategory profileSubcategory = subcategoryDao.findById(subcategoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        profileSubcategory.setIsDeleted(true);
        subcategoryDao.save(profileSubcategory);
    }
}
