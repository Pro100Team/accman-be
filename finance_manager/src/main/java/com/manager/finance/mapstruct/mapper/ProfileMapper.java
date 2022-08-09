package com.manager.finance.mapstruct.mapper;

import com.manager.finance.user.model.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "id", ignore = true)
    Profile sourceProfileToNewProfile(Profile sourceProfile);
}
