package com.manager.finance.mapstruct.mapper;

import com.manager.finance.user.model.entity.User;
import com.sandbox.model.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "username", source = "login")
    UserResponseDto userToDto(User user);
}
