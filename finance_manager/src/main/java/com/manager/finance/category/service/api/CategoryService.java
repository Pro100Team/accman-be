/**
 * @Autor GapSerg
 * @Create 2022-08-19.08.2022 23:00
 **/

package com.manager.finance.category.service.api;

import com.sandbox.model.CategoryRequestDto;
import com.sandbox.model.CategoryResponseDto;
import com.sandbox.model.TransactionTypeParameter;
import java.util.List;

public interface CategoryService {

    CategoryResponseDto getById(Long id);

    CategoryResponseDto update(Long categoryId, CategoryRequestDto categoryRequestDto);

    void delete(Long id);

    Long save(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> findAllByCategoryType(TransactionTypeParameter categoryType);

}
