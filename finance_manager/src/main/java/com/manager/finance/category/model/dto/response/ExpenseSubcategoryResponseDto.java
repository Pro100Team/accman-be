package com.manager.finance.category.model.dto.response;

import com.manager.finance.category.model.entity.api.CategoryType;
import lombok.Data;

@Data
public class ExpenseSubcategoryResponseDto {
    private Long id;
    private Long parentCategoryId;
    private String name;
    private String color;
    private CategoryType categoryType;
}
