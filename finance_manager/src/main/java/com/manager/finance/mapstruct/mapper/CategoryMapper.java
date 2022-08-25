package com.manager.finance.mapstruct.mapper;

import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.ProfileCategory;
import com.manager.finance.category.model.entity.ProfileSubcategory;
import com.manager.finance.user.model.entity.Profile;
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

    @Mapping(target = "name", source = "profileCategory.categoryId.name")
    CategoryResponseDto toCategoryResponseDto(ProfileCategory profileCategory);

    List<CategoryResponseDto> toCategoryResponseDtoList(
            List<ProfileCategory> allCategoryByType);

    @Mapping(target = "subcategory", source = "subCategories")
    @Mapping(target = "name", source = "profileCategory.categoryId.name")
    CategoryResponseDto toExpenseCategoryResponseDto(
            ProfileCategory profileCategory, List<CategoryResponseDto> subCategories
    );

    @Mapping(target = "name", source = "subcategory.subcategoryId.name")
    CategoryResponseDto toExpenseSubcategoryResponseDto(ProfileSubcategory subcategory);

    @Mapping(target = "category", source = "subcategoryId")
    List<CategoryResponseDto> toExpenseSubcategoryResponseDto(
            List<ProfileSubcategory> profileSubCategory);

    @Mapping(target = "subcategoryId", source = "category")
    @Mapping(target = "id", ignore = true)
    ProfileSubcategory toProfileSubcategory(CategoryRequestDto subcategoryRequestDto,
                                            Category category, Profile profile);

    @Mapping(target = "subcategoryId", source = "category")
    @Mapping(target = "id",ignore = true)
    ProfileSubcategory toProfileSubcategoryForUpdating(
            @MappingTarget ProfileSubcategory subcategory,
            CategoryRequestDto categoryRequestDto,
            Category category);
}
