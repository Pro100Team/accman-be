package com.manager.finance.mapstruct.mapper;

import com.manager.finance.category.model.entity.Category;
import com.manager.finance.wallet.model.entity.Wallet;
import com.sandbox.model.CategoryRequestDto;
import com.sandbox.model.CategoryResponseDto;
import com.sandbox.model.WalletRequestDto;
import com.sandbox.model.WalletResponseDto;
import java.util.Formatter;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    CategoryResponseDto toDto(Category category);

    Category toEntity(CategoryRequestDto categoryRequestDto);


    List<CategoryResponseDto> ListToDtoList(List<Category> categories);
}
