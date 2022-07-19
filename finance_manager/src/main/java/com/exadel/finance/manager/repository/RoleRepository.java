package com.exadel.finance.manager.repository;

import com.exadel.finance.manager.model.Role;
import com.exadel.finance.manager.model.UserRoleName;
import java.util.Optional;

public interface RoleRepository extends SpecificationRepository<Role, Long> {
    Optional<Role> findByRoleName(UserRoleName roleName);
}
