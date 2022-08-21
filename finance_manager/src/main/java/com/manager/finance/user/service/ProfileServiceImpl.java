package com.manager.finance.user.service;

import com.manager.finance.user.dao.ProfileDao;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.user.service.api.UserService;
import com.manager.finance.util.TimeZoneUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;
    private final UserService userService;

    @Override
    public void deleteProfile() {
        Profile activeProfile = findByUserIdWithValidation();
        activeProfile.setIsDeleted(true);
        profileDao.save(activeProfile);
    }

    @Override
    public Profile findByUserIdWithValidation() {
        User user = userService.getByUserHolder();
        Profile profile = profileDao.findProfileByUserIdAndIsDeleted(user, false);
        if (profile == null) {
            return createDefaultProfile();
        }
        return profile;
    }

    private Profile createDefaultProfile() {
        User user = userService.getByUserHolder();
        Profile profile = new Profile();
        profile.setIsDeleted(false);
        profile.setDtUpdate(TimeZoneUtils.getGmtCurrentDate());
        profile.setUserId(user);
        Profile createdProfile = profileDao.save(profile);
        return createdProfile;
    }

}
