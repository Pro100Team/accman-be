package com.manager.finance.user.service;

import com.manager.finance.mapstruct.mapper.ProfileMapper;
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
    private final ProfileMapper profileMapper;

    @Override
    public void deleteProfile() {
        Profile activeProfile = findByUserId();
        if (activeProfile == null) {
            activeProfile = createDefaultProfile();
        }
        if (activeProfile != null) {
            activeProfile.setIsDeleted(true);
            profileDao.save(activeProfile);
        }
    }

    @Override
    public Profile findByUserId() {
        User user = userService.getByUserHolder();
        return profileDao.findProfileByUserIdAndIsDeleted(user, false);
    }

    @Override
    public Profile createDefaultProfile() {
        User user = userService.getByUserHolder();
        Profile profile =
                profileMapper.mapToNewProfile(false, TimeZoneUtils.getGmtCurrentDate(), user);

        Profile createdProfile = profileDao.save(profile);
        createDefaultCategories(createdProfile);
        return createdProfile;
    }

    public void createDefaultCategories(Profile profile) {

    }
}
