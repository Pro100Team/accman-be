package com.manager.finance.mapstruct.mapper;

import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;
import java.time.LocalDateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "isDeleted", source = "isDeleted")
    @Mapping(target = "userId", source = "user")
    @Mapping(target = "dtUpdate", source = "gmtCurrentDate")
    @Mapping(target = "id", ignore = true)
    Profile mapToNewProfile(Boolean isDeleted, LocalDateTime gmtCurrentDate, User user);
}
