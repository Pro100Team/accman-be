package com.manager.finance.user.service;

import com.manager.finance.mapstruct.mapper.ProfileMapper;
import com.manager.finance.user.dao.ProfileDao;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.util.TimeZoneUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;
    private final ProfileMapper profileMapper;

    @Override
    public void deleteProfile(User id) {
        Profile activeProfile = findByUserIdWithValidation(id);
        activeProfile.setIsDeleted(true);
        profileDao.save(activeProfile);
    }

    public Profile findActiveProfileByUserId(User userId) {
        return profileDao.findProfileByUserIdAndIsDeleted(userId, false);
    }

    @Override
    public Profile findByUserIdWithValidation(User userId) {
        Profile profile = findActiveProfileByUserId(userId);
        if (profile == null) {
            return createDefaultProfile(userId);
        }
        return profile;
    }

    private Profile createDefaultProfile(User userId) {
        Pageable pageable = PageRequest.of(0, 1,
                Sort.by("dtUpdate").descending());
        Profile sourceProfile =
                profileDao.findProfileByUserId(userId, pageable).getContent().get(0);
        Profile profile = profileMapper.sourceProfileToNewProfile(sourceProfile);
        profile.setIsDeleted(false);
        profile.setDtUpdate(TimeZoneUtils.getGmtCurrentDate());
        return profileDao.save(profile);
    }
}
