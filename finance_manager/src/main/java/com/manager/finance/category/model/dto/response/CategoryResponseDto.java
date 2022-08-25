package com.manager.finance.category.model.dto.response;

import com.manager.finance.category.model.entity.api.CategoryType;
import lombok.Data;

@Data
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String color;
    private CategoryType categoryType;
}
