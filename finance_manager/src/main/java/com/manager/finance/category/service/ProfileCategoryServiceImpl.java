package com.manager.finance.category.service;

import com.manager.finance.category.dao.ProfileCategoryDao;
import com.manager.finance.category.exception.CategoryNotFoundException;
import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.api.CategoryType;
import com.manager.finance.category.service.api.CategoryService;
import com.manager.finance.category.service.api.ProfileCategoryService;
import com.manager.finance.mapstruct.mapper.CategoryMapper;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.service.api.ProfileService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileCategoryServiceImpl implements ProfileCategoryService {
    private final ProfileService profileService;
    private final CategoryMapper categoryMapper;
    private final ProfileCategoryDao profileCategoryDao;
    private final CategoryService categoryService;

    @Override
    public Long save(CategoryRequestDto categoryRequestDto) {
        String name = categoryRequestDto.getName();
        Category category = categoryService.getByName(name);
        if (category == null) {
            category = categoryService.save(name);
        }
        ProfileCategory profileCategory =
                categoryMapper.toProfileCategory(categoryRequestDto, category);
        Profile profile = profileService.findByUserId();
        if (profile == null) {
            profile = profileService.createDefaultProfile();
        }
        profileCategory.setProfileId(profile);
        profileCategory.setIsDeleted(false);
        return profileCategoryDao.save(profileCategory).getId();
    }

    @Override
    public CategoryResponseDto update(Long categoryId,
                                      CategoryRequestDto categoryRequestDto) {
        ProfileCategory profileCategory = profileCategoryDao.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException(
                        "Category with id: " + categoryId + " not found"));
        Category category = categoryService.getByName(categoryRequestDto.getName());
        if (category == null) {
            categoryService.save(categoryRequestDto.getName());
        }
        profileCategory = profileCategoryDao.save(
                categoryMapper.toProfileCategoryForUpdating(profileCategory,
                        categoryRequestDto,
                        category));
        return categoryMapper.toCategoryResponseDto(profileCategory);
    }

    @Override
    public void delete(Long id) {
        ProfileCategory category = profileCategoryDao.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        category.setIsDeleted(true);
        profileCategoryDao.save(category);
    }

    @Override
    public List<ProfileCategory> findAllIncomeCategory() {
        Profile profile = profileService.findByUserId();
        if (profile == null) {
            profile = profileService.createDefaultProfile();
        }
        return profileCategoryDao.findByCategoryTypeAndProfileIdAndIsDeleted(CategoryType.INCOME,
                profile,
                false);
    }

    @Override
    public List<ProfileCategory> findAllExpenseCategory() {
        Profile profile = profileService.findByUserId();
        if (profile == null) {
            profile = profileService.createDefaultProfile();
        }
        return profileCategoryDao.findByCategoryTypeAndProfileIdAndIsDeleted(CategoryType.EXPENSE,
                profile,
                false);
    }

    @Override
    public ProfileCategory getById(Long parentCategoryId) {
        return profileCategoryDao.findById(parentCategoryId)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category with id: " + parentCategoryId + " not found"));
    }
}
