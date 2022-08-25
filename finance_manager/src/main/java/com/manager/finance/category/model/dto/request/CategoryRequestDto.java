package com.manager.finance.category.model.dto.request;

import com.manager.finance.category.model.entity.api.Type;
import lombok.Data;

@Data
public class CategoryRequestDto {

    private String name;

    private String color;

    private Type type;
}
