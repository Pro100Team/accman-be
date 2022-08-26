package com.manager.finance.category.controller;

import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.api.CategoryType;
import com.manager.finance.category.service.api.ProfileCategoryService;
import com.manager.finance.category.service.api.ProfileSubcategoryService;
import com.manager.finance.mapstruct.mapper.CategoryMapper;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.service.api.ProfileService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CategoryController {
    private final ProfileCategoryService categoryService;
    private final ProfileSubcategoryService subcategoryService;
    private final CategoryMapper categoryMapper;
    private final ProfileService profileService;

    @PostMapping("/categories")
    public ResponseEntity<Long> createCategory(@RequestBody
                                               CategoryRequestDto categoryRequestDto) {
        Profile profile = profileService.findByUserIdOrCreate();
        return new ResponseEntity<>(categoryService.save(categoryRequestDto, profile),
                HttpStatus.OK);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<?>> getAllIncomeCategories(
            @RequestParam(name = "categoryType") String categoryType) {
        Profile profile = profileService.findByUserIdOrCreate();
        List<CategoryResponseDto> categoryResponseDto = new ArrayList<>();
        if (categoryType.equals(CategoryType.INCOME.name())) {
            List<ProfileCategory> allCategoryByType =
                    categoryService.findAllIncomeCategory(profile);

            categoryResponseDto =
                    categoryMapper.toCategoryResponseDtoList(allCategoryByType);
            categoryResponseDto.forEach(
                    categoryResponseDto1 -> categoryResponseDto1.setCategoryType(
                            CategoryType.INCOME));
        } else if (categoryType.equals(CategoryType.EXPENSE.name())) {
            List<ProfileCategory> profileCategoryList =
                    categoryService.findAllExpenseCategory(profile);
            for (ProfileCategory profileCategory : profileCategoryList) {
                List<CategoryResponseDto> subcategories =
                        subcategoryService.findAllByParentCategoryId(profileCategory.getId(),
                                profile);
                subcategories.forEach(expenseCategories -> expenseCategories.setCategoryType(
                        CategoryType.EXPENSE));
                categoryResponseDto.add(
                        categoryMapper.toExpenseCategoryResponseDto(profileCategory,
                                subcategories));
                profileCategory.setCategoryType(CategoryType.EXPENSE);
            }
        }
        return new ResponseEntity<>(categoryResponseDto,
                HttpStatus.OK);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(
            @PathVariable("id") Long categoryId) {
        ProfileCategory category = categoryService.getById(categoryId);
        return new ResponseEntity<>(categoryMapper.toCategoryResponseDto(category), HttpStatus.OK);
    }

    @PutMapping("/categories/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategoryById(
            @PathVariable("id") Long categoryId,
            @RequestBody CategoryRequestDto categoryRequestDto) {
        Profile profile = profileService.findByUserIdOrCreate();
        return new ResponseEntity<>(categoryService.update(categoryId, categoryRequestDto, profile),
                HttpStatus.OK);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") Long categoryId) {
        categoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/subcategories/{id}")
    public ResponseEntity<Void> deleteSubcategoryById(@PathVariable("id") Long subcategoryId) {
        subcategoryService.delete(subcategoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/subcategories")
    public ResponseEntity<Long> createSubcategory(@RequestBody
                                                  CategoryRequestDto categoryRequestDto) {
        Profile profile = profileService.findByUserIdOrCreate();
        categoryService.getById(categoryRequestDto.getParentCategoryId());
        return new ResponseEntity<>(subcategoryService.save(categoryRequestDto, profile),
                HttpStatus.OK);
    }

    @PutMapping("/subcategories/{id}")
    public ResponseEntity<CategoryResponseDto> updateSubcategoryById(
            @PathVariable("id") Long categoryId,
            @RequestBody CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(subcategoryService.update(categoryId, categoryRequestDto),
                HttpStatus.OK);
    }
}
