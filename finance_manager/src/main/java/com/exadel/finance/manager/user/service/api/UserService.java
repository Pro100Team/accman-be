package com.exadel.finance.manager.user.service.api;

import com.exadel.finance.manager.user.model.entity.User;
import com.exadel.finance.manager.user.model.entity.UserModel;

public interface UserService {

    User createUser(UserModel user);

    User readUser();

    User updateUser(UserModel user);

    void deleteUser();

    User getLoggedInUser();
}
