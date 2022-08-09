package com.exadel.managger.finance.user.service.api;

import com.exadel.managger.finance.user.model.entity.User;

public interface UserService {
    User getByLogin(String login);

    User getByUserHolder();
}
