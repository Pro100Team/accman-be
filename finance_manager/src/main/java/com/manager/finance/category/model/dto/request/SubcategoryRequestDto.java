package com.manager.finance.category.model.dto.request;

import lombok.Data;

@Data
public class SubcategoryRequestDto {

    private String name;

    private String color;

    private Long parentCategoryId;
}
