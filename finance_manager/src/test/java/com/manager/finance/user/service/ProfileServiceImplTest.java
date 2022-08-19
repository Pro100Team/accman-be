package com.manager.finance.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.manager.finance.config.AbstractTest;
import com.manager.finance.user.dao.ProfileDao;
import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.model.entity.api.Country;
import com.manager.finance.user.model.entity.api.Role;
import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.user.service.api.UserService;
import com.manager.finance.util.TimeZoneUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class ProfileServiceImplTest extends AbstractTest {
    @Mock
    private UserService userService;
    @Mock
    private ProfileDao profileDao;
    private ProfileService profileService;

    @BeforeEach
    public void initialize() {
        profileService = new ProfileServiceImpl(profileDao, userService, null);
    }

    @Test
    public void findByUserIdWithValidation() {
        User user = User.builder().id(1L).login("testLogin").password("test").role(Role.ROLE_USER)
                .build();
        Profile profile = Profile.builder().id(1L).country(Country.POLAND).isDeleted(false)
                .dtUpdate(TimeZoneUtils.getGmtCurrentDate()).firstName("test").lastName("test")
                .userId(user).build();
        Mockito.when(userService.getByUserHolder()).thenReturn(user);
        Mockito.when(profileDao.findProfileByUserIdAndIsDeleted(user, false))
                .thenReturn(profile);
        Profile byUserIdWithValidation = profileService.findByUserIdWithValidation();
        assertEquals(profile, byUserIdWithValidation);
    }

    @Test
    public void deleteProfile() {
        User user = User.builder().id(1L).login("testLogin").password("test").role(Role.ROLE_USER)
                .build();
        Profile profile = Profile.builder().id(1L).country(Country.POLAND).isDeleted(false)
                .dtUpdate(TimeZoneUtils.getGmtCurrentDate()).firstName("test").lastName("test")
                .userId(user).build();
        Mockito.when(userService.getByUserHolder()).thenReturn(user);
        Mockito.when(profileDao.findProfileByUserIdAndIsDeleted(user, false))
                .thenReturn(profile);
        profileService.deleteProfile();
    }
}
