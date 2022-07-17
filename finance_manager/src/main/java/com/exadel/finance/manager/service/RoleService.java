package com.exadel.finance.manager.service;

import com.exadel.finance.manager.model.Role;
import java.util.List;

public interface RoleService {
    Role save(Role role);

    Role findById(Long id);

    List<Role> findAll();

    void deleteById(Long id);
}
