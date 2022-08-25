package com.manager.finance.category.model.dto.response;

import com.manager.finance.category.model.entity.api.CategoryType;
import java.util.List;
import lombok.Data;

@Data
public class ExpenseCategoryResponseDto {
    private Long id;
    private String name;
    private String color;
    private CategoryType categoryType;
    private List<ExpenseSubcategoryResponseDto> subcategory;
}
