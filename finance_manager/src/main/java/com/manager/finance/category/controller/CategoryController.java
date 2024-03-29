/**
 * @Autor GapSerg
 * @Create 2022-08-11.08.2022 13:29
 **/

package com.manager.finance.category.controller;

import com.manager.finance.category.service.api.CategoryService;
import com.sandbox.api.CategoriesApi;
import com.sandbox.model.CategoryRequestDto;
import com.sandbox.model.CategoryResponseDto;
import com.sandbox.model.TransactionTypeParameter;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class CategoryController implements CategoriesApi {

    private final CategoryService categoryService;

    @Override
    public ResponseEntity<Long> createCategory(@Valid CategoryRequestDto categoryRequestDto) {
        return new ResponseEntity<>(categoryService.save(categoryRequestDto),
                HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteCategoryById(Long categoryId) {

        return categoryService.delete(categoryId);

    }

    @Override
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(
            @Valid TransactionTypeParameter categoryType) {

        return ResponseEntity.ok(categoryService.findAllByCategoryType(categoryType));
    }

    @Override
    public ResponseEntity<CategoryResponseDto> getCategoryById(Long categoryId) {

        return ResponseEntity.ok(categoryService.getById(categoryId));
    }

    @Override
    public ResponseEntity<CategoryResponseDto> updateCategoryById(
            Long categoryId, @Valid CategoryRequestDto categoryRequestDto) {
        return ResponseEntity.ok(categoryService.update(categoryId, categoryRequestDto));
    }
}
