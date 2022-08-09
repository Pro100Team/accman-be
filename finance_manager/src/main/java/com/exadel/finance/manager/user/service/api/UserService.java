package com.exadel.finance.manager.user.service.api;

import com.exadel.finance.manager.user.model.entity.User;

public interface UserService {
    User getByLogin(String login);

    User getByUserHolder();
}
