package com.manager.finance.user.service.api;

import com.manager.finance.user.model.entity.User;

public interface UserService {
    User getByLogin(String login);

    User getByUserHolder();

    User getById(Long id);
}
