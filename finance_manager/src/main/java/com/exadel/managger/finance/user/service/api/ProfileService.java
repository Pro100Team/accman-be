package com.exadel.managger.finance.user.service.api;

import com.exadel.managger.finance.user.model.entity.Profile;
import com.exadel.managger.finance.user.model.entity.User;

public interface ProfileService {
    Profile findByUserId(User user);
}
