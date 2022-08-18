package com.manager.finance.user.service;

import com.manager.finance.mapstruct.mapper.ProfileMapper;
import com.manager.finance.user.dao.ProfileDao;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.user.service.api.UserService;
import com.manager.finance.util.TimeZoneUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;
    private final UserService userService;
    private final ProfileMapper profileMapper;

    @Override
    public void deleteProfile() {
        Profile activeProfile = findByUserIdWithValidation();
        activeProfile.setIsDeleted(true);
        profileDao.save(activeProfile);
    }

    @Override
    public Profile findActiveProfileByUserId() {
        User user = userService.getByUserHolder();
        return profileDao.findProfileByUserIdAndIsDeleted(user, false);
    }

    @Override
    public Profile findByUserIdWithValidation() {
        Profile profile = findActiveProfileByUserId();
        if (profile == null) {
            return createDefaultProfile();
        }
        return profile;
    }

    private Profile createDefaultProfile() {
        User user = userService.getByUserHolder();
        Sort sort = Sort.by("dtUpdate").descending();
        Profile sourceProfile = profileDao.findProfileByUserId(user, sort);
        Profile profile = profileMapper.sourceProfileToNewProfile(sourceProfile);
        profile.setIsDeleted(false);
        profile.setDtUpdate(TimeZoneUtils.getGmtCurrentDate());
        return profileDao.save(profile);
    }
}
