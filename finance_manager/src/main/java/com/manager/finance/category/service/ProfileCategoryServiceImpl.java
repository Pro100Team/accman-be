package com.manager.finance.category.service;

import com.manager.finance.category.dao.ProfileCategoryDao;
import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.DefaultCategory;
import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.api.CategoryType;
import com.manager.finance.category.service.api.CategoryService;
import com.manager.finance.category.service.api.DefaultCategoryService;
import com.manager.finance.category.service.api.ProfileCategoryService;
import com.manager.finance.exception.category.CategoryNotFoundException;
import com.manager.finance.mapstruct.mapper.CategoryMapper;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileCategoryServiceImpl implements ProfileCategoryService {
    private final CategoryMapper categoryMapper;
    private final ProfileCategoryDao profileCategoryDao;
    private final CategoryService categoryService;
    private final DefaultCategoryService defaultCategoryService;

    @Override
    public Long save(CategoryRequestDto categoryRequestDto, Profile profile) {
        Category category = categoryService.getByNameOrCreate(categoryRequestDto.getName());
        ProfileCategory profileCategory =
                categoryMapper.toProfileCategory(categoryRequestDto, category);
        profileCategory.setProfileId(profile);
        profileCategory.setIsDeleted(false);
        return profileCategoryDao.save(profileCategory).getId();
    }

    @Override
    public CategoryResponseDto update(Long categoryId,
                                      CategoryRequestDto categoryDto, Profile profile) {
        ProfileCategory profileCategory = profileCategoryDao.findById(categoryId).orElseThrow(
                () -> new CategoryNotFoundException(
                        "Category with id: " + categoryId + " not found"));
        Category category = categoryService.getByNameOrCreate(categoryDto.getName());
        ProfileCategory entity = categoryMapper.toProfileCategoryForUpdating(profileCategory,
                categoryDto,
                category);
        profileCategory.setProfileId(profile);
        profileCategory = profileCategoryDao.save(entity);
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
    public List<ProfileCategory> findAllIncomeCategory(Profile profile) {
        return profileCategoryDao.findByCategoryTypeAndProfileIdAndIsDeleted(CategoryType.INCOME,
                profile,
                false);
    }

    @Override
    public List<ProfileCategory> findAllExpenseCategory(Profile profile) {
        return profileCategoryDao.findByCategoryTypeAndProfileIdAndIsDeleted(CategoryType.EXPENSE,
                profile,
                false);
    }

    @Override
    public ProfileCategory getById(Long parentCategoryId) {
        return profileCategoryDao.findByIdAndIsDeleted(parentCategoryId, false)
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category with id: " + parentCategoryId + " not found"));
    }

    @Override
    public void createDefaultCategory(Profile profile) {
        List<DefaultCategory> categoryList = defaultCategoryService.getAll();
        for (DefaultCategory defaultCategory : categoryList) {
            Category category = categoryService.getByNameOrCreate(defaultCategory.getName());
            ProfileCategory profileCategory =
                    categoryMapper.toCreateDefaultProfileCategory(defaultCategory, category);
            profileCategory.setIsDeleted(false);
            profileCategory.setProfileId(profile);
            profileCategoryDao.save(profileCategory);
        }
    }

    @Override
    public ProfileCategory getByCategoryIdAndProfile(Category parentCategory, Profile profile) {
        return profileCategoryDao.findByCategoryIdAndProfileId(parentCategory, profile);
    }
}
