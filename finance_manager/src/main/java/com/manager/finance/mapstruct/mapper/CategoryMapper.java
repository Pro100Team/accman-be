package com.manager.finance.mapstruct.mapper;

import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.DefaultCategory;
import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.ProfileSubcategory;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "categoryId", source = "category")
    @Mapping(target = "id", ignore = true)
    ProfileCategory toProfileCategory(CategoryRequestDto categoryRequestDto, Category category);

    @Mapping(target = "categoryId", source = "category")
    @Mapping(target = "id", ignore = true)
    ProfileCategory toProfileCategoryForUpdating(
            @MappingTarget ProfileCategory profileCategory,
            CategoryRequestDto categoryRequestDto,
            Category category);

    @Mapping(target = "categoryId", source = "category")
    @Mapping(target = "id", ignore = true)
    ProfileCategory toCreateDefaultProfileCategory(DefaultCategory defaultCategory,
                                                   Category category);

    @Mapping(target = "name", source = "profileCategory.categoryId.name")
    CategoryResponseDto toCategoryResponseDto(ProfileCategory profileCategory);

    @Mapping(target = "subcategory", source = "subCategories")
    @Mapping(target = "name", source = "profileCategory.categoryId.name")
    CategoryResponseDto toExpenseCategoryResponseDto(
            ProfileCategory profileCategory, List<CategoryResponseDto> subCategories
    );

    @Mapping(target = "name", source = "subcategory.subcategoryId.name")
    CategoryResponseDto toExpenseSubcategoryResponseDto(ProfileSubcategory subcategory);

    List<CategoryResponseDto> toCategoryResponseDtoList(
            List<ProfileCategory> allCategoryByType);

}
