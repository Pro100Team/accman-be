package com.exadel.finance.manager.mapstruct.mapper;

import com.exadel.finance.manager.user.model.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "id", ignore = true)
    Profile sourceProfileToNewProfile(Profile sourceProfile);
}
