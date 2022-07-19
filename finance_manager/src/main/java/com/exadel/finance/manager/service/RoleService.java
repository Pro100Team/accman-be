package com.exadel.finance.manager.service;

import com.exadel.finance.manager.model.Role;
import com.exadel.finance.manager.model.UserRoleName;

public interface RoleService extends GenericService<Role> {
    Role findByRoleName(UserRoleName roleName);
}
