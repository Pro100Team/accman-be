package com.exadel.finance.manager.service;

import com.exadel.finance.manager.model.User;

public interface UserService extends GenericService<User> {
    User findByEmail(String email);
}
