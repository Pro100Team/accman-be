package com.exadel.finance.manager.user.service.api;

import com.exadel.finance.manager.user.model.entity.Profile;
import com.exadel.finance.manager.user.model.entity.User;

public interface ProfileService {
    Profile findByUserId(User user);
}
