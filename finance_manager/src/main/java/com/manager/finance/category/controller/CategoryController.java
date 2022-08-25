package com.manager.finance.category.controller;

import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.request.SubcategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.dto.response.ExpenseCategoryResponseDto;
import com.manager.finance.category.model.dto.response.ExpenseSubcategoryResponseDto;
import com.manager.finance.category.model.entity.ProfileCategory;
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
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CategoryController {
    private final ProfileCategoryService categoryService;
    private final ProfileSubcategoryService subcategoryService;
    private final CategoryMapper categoryMapper;
    private final ProfileService profileService;

    @PostMapping("/category/create")
    public ResponseEntity<Long> createCategory(@RequestBody
                                               CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.save(categoryRequestDto), HttpStatus.OK);
    }

    @PostMapping("/subcategory/expense/create")
    public ResponseEntity<Long> createSubcategory(@RequestBody
                                                  SubcategoryRequestDto categoryRequestDto) {
        categoryService.getById(categoryRequestDto.getParentCategoryId());
        return new ResponseEntity<>(subcategoryService.save(categoryRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/category/{id}")
    public ResponseEntity<Void> deleteCategoryById(@PathVariable("id") Long categoryId) {
        categoryService.delete(categoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/subcategory/{id}")
    public ResponseEntity<Void> deleteSubcategoryById(@PathVariable("id") Long subcategoryId) {
        subcategoryService.delete(subcategoryId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/category/income")
    public ResponseEntity<List<CategoryResponseDto>> getAllIncomeCategories() {
        List<ProfileCategory> allCategoryByType =
                categoryService.findAllIncomeCategory();
        return new ResponseEntity<>(categoryMapper.toCategoryResponseDtoList(allCategoryByType),
                HttpStatus.OK);
    }

    @GetMapping("/category/expense")
    public ResponseEntity<List<ExpenseCategoryResponseDto>> getAllExpenseCategories() {

        List<ProfileCategory> profileCategoryList =
                categoryService.findAllExpenseCategory();

        Profile profile = profileService.findByUserId();
        if (profile == null) {
            profile = profileService.createDefaultProfile();
        }
        List<ExpenseCategoryResponseDto> expenseCategoryResponseDtoList = new ArrayList<>();

        for (ProfileCategory profileCategory : profileCategoryList) {
            List<ExpenseSubcategoryResponseDto> subcategories =
                    subcategoryService.findAllByCategoryId(profileCategory.getId(), profile);
            expenseCategoryResponseDtoList.add(
                    categoryMapper.toExpenseCategoryResponseDto(profileCategory,
                            subcategories));
        }

        return new ResponseEntity<>(expenseCategoryResponseDtoList, HttpStatus.OK);
    }

    @GetMapping("/category/update/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(
            @PathVariable("id") Long categoryId) {
        ProfileCategory category = categoryService.getById(categoryId);
        return new ResponseEntity<>(categoryMapper.toCategoryResponseDto(category), HttpStatus.OK);
    }

    @PutMapping("/category/update/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategoryById(
            @PathVariable("id") Long categoryId,
            @RequestBody CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.update(categoryId, categoryRequestDto),
                HttpStatus.OK);
    }

}
