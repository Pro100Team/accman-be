package com.manager.finance.user.service;

import com.manager.finance.config.AbstractTest;
import com.manager.finance.security.service.UserHolder;
import com.manager.finance.user.dao.UserDao;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.model.entity.api.Role;
import com.manager.finance.user.service.api.UserService;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class UserServiceImplTest extends AbstractTest {
    @Mock
    private UserHolder userHolder;
    @Mock
    private UserDao userDao;

    private UserService userService;

    @BeforeEach
    public void initialize() {
        userService = new UserServiceImpl(userDao, userHolder);
    }

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
