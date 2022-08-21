package com.manager.finance.user.service;

import com.manager.finance.category.dao.CategoryDao;
import com.manager.finance.category.model.entity.Category;
import com.manager.finance.mapstruct.mapper.ProfileMapper;
import com.manager.finance.user.dao.ProfileDao;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.user.service.api.UserService;
import com.manager.finance.util.TimeZoneUtils;
import com.sandbox.model.TransactionTypeParameter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final ProfileDao profileDao;
    private final UserService userService;
    private final ProfileMapper profileMapper;
    private final CategoryDao categoryDao;

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
        Sort sort = Sort.by("dtUpdate").descending();
        Profile sourceProfile = profileDao.findProfileByUserId(user, sort);
        Profile profile = profileMapper.sourceProfileToNewProfile(sourceProfile);
        profile.setIsDeleted(false);
        profile.setDtUpdate(TimeZoneUtils.getGmtCurrentDate());
        Profile createdProfile = profileDao.save(profile);
        createDefaultCategories(createdProfile);
        return createdProfile;

    }

    public void createDefaultCategories(Profile profile) {
        categoryDao.save(new Category(
                "Food", "#FF0000", TransactionTypeParameter.EXPENSE, profile));
        categoryDao.save(new Category(
                "Sport", "#FFA500", TransactionTypeParameter.EXPENSE, profile));
        categoryDao.save(new Category(
                "Pets", "#FFFF00", TransactionTypeParameter.EXPENSE, profile));
        categoryDao.save(new Category(
                "Utilities", "#3CB371", TransactionTypeParameter.EXPENSE, profile));
        categoryDao.save(new Category(
                "Сlothes", "#00FFFF", TransactionTypeParameter.EXPENSE, profile));
        categoryDao.save(new Category(
                "Salary", "#0000CD", TransactionTypeParameter.INCOME, profile));
        categoryDao.save(new Category(
                "Deposit interest", "#9932CC", TransactionTypeParameter.INCOME, profile));
        categoryDao.save(new Category(
                "Dividends by stock", "#C71585", TransactionTypeParameter.INCOME, profile));
    }
}
