package com.manager.finance.category.model.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class ExpenseCategoryResponseDto {
    private Long id;
    private String name;
    private String color;
    private List<ExpenseSubcategoryResponseDto> subcategory;
}
