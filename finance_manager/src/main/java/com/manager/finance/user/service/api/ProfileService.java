package com.manager.finance.user.service.api;

import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;

public interface ProfileService {
    void deleteProfile(User id);

    Profile findByUserIdWithValidation(User user);
}
