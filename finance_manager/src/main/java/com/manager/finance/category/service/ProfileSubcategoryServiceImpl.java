package com.manager.finance.category.service;

import com.manager.finance.category.dao.SubcategoryDao;
import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.DefaultSubcategory;
import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.ProfileSubcategory;
import com.manager.finance.category.service.api.CategoryService;
import com.manager.finance.category.service.api.DefaultSubcategoryService;
import com.manager.finance.category.service.api.ProfileCategoryService;
import com.manager.finance.category.service.api.ProfileSubcategoryService;
import com.manager.finance.exception.category.CategoryNotFoundException;
import com.manager.finance.mapstruct.mapper.CategoryMapper;
import com.manager.finance.mapstruct.mapper.SubcategoryMapper;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileSubcategoryServiceImpl implements ProfileSubcategoryService {
    private final SubcategoryDao subcategoryDao;
    private final CategoryMapper categoryMapper;
    private final SubcategoryMapper subcategoryMapper;
    private final CategoryService categoryService;
    private final DefaultSubcategoryService defaultSubcategoryService;
    private final ProfileCategoryService profileCategoryService;

    @Override
    public List<CategoryResponseDto> findAllByParentCategoryId(Long categoryId,
                                                               Profile profileId) {
        List<ProfileSubcategory> profileSubCategory =
                subcategoryDao.findByParentCategoryIdAndProfileIdAndIsDeleted(categoryId, profileId,
                        false);
        return subcategoryMapper.toExpenseSubcategoryResponseDtoList(profileSubCategory);
    }

    @Override
    public Long save(CategoryRequestDto subcategoryRequestDto, Profile profile) {
        Category category = categoryService.getByNameOrCreate(subcategoryRequestDto.getName());
        if (category == null) {
            category = categoryService.save(subcategoryRequestDto.getName());
        }

        ProfileSubcategory profileSubcategory =
                subcategoryMapper.toProfileSubcategory(subcategoryRequestDto, category, profile);
        profileSubcategory.setIsDeleted(false);
        profileSubcategory.setProfileId(profile);
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

    @Override
    public CategoryResponseDto update(Long categoryId, CategoryRequestDto categoryRequestDto) {
        Category category = categoryService.getByNameOrCreate(categoryRequestDto.getName());
        if (category == null) {
            category = categoryService.save(categoryRequestDto.getName());
        }
        ProfileSubcategory subcategory = subcategoryDao.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found"));
        subcategory =
                subcategoryMapper.toProfileSubcategoryForUpdating(subcategory, categoryRequestDto,
                        category);
        subcategory = subcategoryDao.save(subcategory);
        return categoryMapper.toExpenseSubcategoryResponseDto(subcategoryDao.save(subcategory));
    }

    @Override
    public void createDefaultCategory(Profile profile) {
        List<DefaultSubcategory> categoryList = defaultSubcategoryService.getAll();
        for (DefaultSubcategory defaultCategory : categoryList) {

            Category parentCategory = categoryService.getByNameOrCreate(
                    defaultCategory.getParentCategoryId().getName());

            ProfileCategory parentProfileCategory =
                    profileCategoryService.getByCategoryIdAndProfile(parentCategory, profile);

            Category category =
                    categoryService.getByNameOrCreate(defaultCategory.getName());

            ProfileSubcategory profileCategory =
                    subcategoryMapper.toCreateDefaultProfileSubcategory(defaultCategory, category,
                            parentProfileCategory.getId());

            profileCategory.setIsDeleted(false);

            profileCategory.setProfileId(profile);

            subcategoryDao.save(profileCategory);
        }
    }

    @Override
    public ProfileSubcategory findByCategoryId(Category category, Profile profile) {
        return subcategoryDao.findBySubcategoryIdAndProfileId(category, profile);
    }
}
