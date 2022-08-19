package com.manager.finance.user.service;

import com.manager.finance.config.AbstractTest;
import com.manager.finance.security.service.UserHolder;
import com.manager.finance.user.dao.UserDao;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.model.entity.api.Role;
import com.manager.finance.user.service.api.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
public class UserServiceImplTest extends AbstractTest {
    @MockBean
    private UserHolder userHolder;
    @MockBean
    private UserDao userDao;
    @Autowired
    private UserService userService;

    @Test
    public void getByUserHolder() {
        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken("login", null,
                        null);
        SecurityContextHolder.getContext().setAuthentication(auth);
        User user = User.builder().role(Role.ROLE_USER).password("password").login("login").id(1L)
                .build();
        Mockito.when(userHolder.getAuthentication()).thenReturn(auth);
        Mockito.when(userDao.findUserByLogin(user.getLogin())).thenReturn(user);
        User byUserHolder = userService.getByUserHolder();
        Assertions.assertEquals(user, byUserHolder);
    }

    @Test
    public void getByLogin() {
        User user = User.builder().role(Role.ROLE_USER).password("password").login("login").id(1L)
                .build();
        Mockito.when(userDao.findUserByLogin(user.getLogin())).thenReturn(user);
        User byUserHolder = userService.getByLogin(user.getLogin());
        Assertions.assertEquals(user, byUserHolder);
    }

    @Test
    public void getById() {
        User user = User.builder().role(Role.ROLE_USER).password("password").login("login").id(1L)
                .build();
        Mockito.when(userDao.findById(user.getId())).thenReturn(Optional.of(user));
        User byUserHolder = userService.getById(user.getId());
        Assertions.assertEquals(user, byUserHolder);
    }
}
