package com.exadel.finance.manager.user.service;

import com.exadel.finance.manager.mapstruct.mapper.ProfileMapper;
import com.exadel.finance.manager.user.dao.ProfileDao;
import com.exadel.finance.manager.user.service.api.ProfileService;
import com.exadel.finance.manager.user.model.entity.Profile;
import com.exadel.finance.manager.user.model.entity.User;
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
    public Profile findByUserId(User userId) {
        Profile profile =
                profileDao.findProfileByUserIdAndIsDeleted(userId, false);
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
        return profileDao.save(profileMapper.sourceProfileToNewProfile(sourceProfile));
    }
}
