package com.manager.finance.user.service;

import com.manager.finance.category.service.api.ProfileCategoryService;
import com.manager.finance.category.service.api.ProfileSubcategoryService;
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
    private final ProfileCategoryService categoryService;
    private final ProfileSubcategoryService profileSubcategoryService;

    @Override
    public void deleteProfile() {
        Profile activeProfile = findByUserIdOrCreate();
        if (activeProfile != null) {
            activeProfile.setIsDeleted(true);
            profileDao.save(activeProfile);
        }
    }

    @Override
    public Profile findByUserIdOrCreate() {
        User user = userService.getByUserHolder();
        Profile profile =
                profileDao.findProfileByUserIdAndIsDeleted(user, false);
        if (profile == null) {
            profile = createDefaultProfile();
            categoryService.createDefaultCategory(profile);
            profileSubcategoryService.createDefaultCategory(profile);
        }
        return profile;
    }

    private Profile createDefaultProfile() {
        User user = userService.getByUserHolder();
        Profile profile =
                profileMapper.mapToNewProfile(false, TimeZoneUtils.getGmtCurrentDate(), user);
        return profileDao.save(profile);
    }

}
