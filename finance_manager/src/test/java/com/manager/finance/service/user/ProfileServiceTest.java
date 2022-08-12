package com.manager.finance.service.user;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.manager.finance.user.model.entity.Profile;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.user.service.api.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileServiceTest {
    private static Profile profile;
    private static User user;
    @Autowired
    private ProfileService profileService;
    @Autowired
    private UserService userService;

    @BeforeAll
    public static void createContext() {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("Sergey@exadel.com", null,
                        null);
        SecurityContextHolder.getContext().setAuthentication(auth);
    }

    @AfterAll
    public static void cleanTestSource() {
    }

    @Test()
    @Order(1)
    public void getProfileByUserId() {
        user = userService.getByUserHolder();
        profile = profileService.findActiveProfileByUserId(userService.getByUserHolder());
        assertNotNull(profile);
    }

    @Test()
    @Order(2)
    public void deleteProfile() {
        profileService.deleteProfile(user);
        Profile newProfile = profileService.findByUserIdWithValidation(user);
        assertAll(
                () -> assertNotNull(newProfile),
                () -> assertNotEquals(newProfile.getId(), profile.getId())
        );
    }

}