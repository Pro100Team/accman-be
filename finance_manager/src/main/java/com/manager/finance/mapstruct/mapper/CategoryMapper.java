package com.manager.finance.mapstruct.mapper;

import com.manager.finance.category.model.entity.Category;
import com.sandbox.model.CategoryRequestDto;
import com.sandbox.model.CategoryResponseDto;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto categoryToResponseDto(Category category);

    Category requestDtoToCategory(CategoryRequestDto categoryRequestDto);

    List<CategoryResponseDto> categoryListToResponseDtoList(List<Category> categories);
}
