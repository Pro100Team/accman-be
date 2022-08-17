package com.manager.finance.service.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.manager.finance.user.dao.UserDao;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.model.entity.api.Role;
import com.manager.finance.user.service.api.UserService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("dev")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
    private static User user;
    private static UserDao userDao;

    @Autowired
    private UserService userService;

    @Autowired
    public UserServiceTest(UserDao userDaoInt) {
        userDao = userDaoInt;
    }

    @BeforeAll
    public static void prepareEntity() {
        user = new User();
        user.setRole(Role.ROLE_USER);
        user.setLogin("testLogin");
        user.setPassword("testPassword");
    }

    @AfterAll
    public static void cleanTestSource() {
        userDao.delete(user);
    }

    @Test
    @Order(1)
    public void findByLogin() {
        user = userDao.save(user);
        User userByLogin = userService.getByLogin(user.getLogin());
        assertEquals(userByLogin, user);
    }

    @Test
    @Order(2)
    public void findById() {
        User userById = userService.getById(user.getId());
        assertEquals(userById, user);
    }
}
