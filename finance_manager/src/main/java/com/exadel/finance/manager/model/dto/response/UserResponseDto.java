package com.exadel.finance.manager.model.dto.response;

import java.util.List;
import lombok.Data;

@Data
public class UserResponseDto {
    private Long id;
    private String name;
    private String email;
    private List<RoleResponseDto> roles;
}
