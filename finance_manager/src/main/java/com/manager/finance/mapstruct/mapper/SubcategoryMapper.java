package com.manager.finance.mapstruct.mapper;

import com.manager.finance.category.model.dto.request.CategoryRequestDto;
import com.manager.finance.category.model.dto.response.CategoryResponseDto;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.category.model.entity.DefaultSubcategory;
import com.manager.finance.category.model.entity.ProfileSubcategory;
import com.manager.finance.user.model.entity.Profile;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface SubcategoryMapper {

    @Mapping(target = "subcategoryId", source = "category")
    @Mapping(target = "id", ignore = true)
    ProfileSubcategory toProfileSubcategory(CategoryRequestDto subcategoryRequestDto,
                                            Category category, Profile profile);

    @Mapping(target = "category", source = "subcategoryId")
    List<CategoryResponseDto> toExpenseSubcategoryResponseDtoList(
            List<ProfileSubcategory> profileSubCategory);

    @Mapping(target = "subcategoryId", source = "category")
    @Mapping(target = "id", ignore = true)
    ProfileSubcategory toProfileSubcategoryForUpdating(
            @MappingTarget ProfileSubcategory subcategory,
            CategoryRequestDto categoryRequestDto,
            Category category);

    @Mapping(target = "subcategoryId", source = "category")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "parentCategoryId", source = "parentCategoryId")
    ProfileSubcategory toCreateDefaultProfileSubcategory(DefaultSubcategory defaultCategory,
                                                         Category category, Long parentCategoryId);
}
