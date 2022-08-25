package com.manager.finance.user.service.api;

import com.manager.finance.user.model.entity.Profile;

public interface ProfileService {
    void deleteProfile();

    Profile findByUserId();

    Profile createDefaultProfile();
}
