package com.manager.finance.category.model.dto.response;

import com.manager.finance.category.model.entity.Category;
import com.manager.finance.user.model.entity.Profile;
import lombok.Data;

@Data
public class ExpenseSubcategoryResponseDto {
    private Long id;
    private Profile profileId;
    private Long parentCategoryId;
    private Category category;
    private String color;
}
