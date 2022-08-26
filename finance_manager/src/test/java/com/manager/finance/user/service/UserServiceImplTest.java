package com.manager.finance.user.service;

import com.manager.finance.security.service.UserHolder;
import com.manager.finance.user.dao.UserDao;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.model.entity.api.Role;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {
    @Mock
    private UserHolder userHolder;
    @Mock
    private UserDao userDao;
    @InjectMocks
    private UserServiceImpl userServiceImpl;

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
        User byUserHolder = userServiceImpl.getByUserHolder();
        Assertions.assertEquals(user, byUserHolder);
    }

    @Test
    public void getByLogin() {
        User user = User.builder().role(Role.ROLE_USER).password("password").login("login").id(1L)
                .build();
        Mockito.when(userDao.findUserByLogin(user.getLogin())).thenReturn(user);
        User byUserHolder = userServiceImpl.getByLogin(user.getLogin());
        Assertions.assertEquals(user, byUserHolder);
    }

    @Test
    public void getById() {
        User user = User.builder().role(Role.ROLE_USER).password("password").login("login").id(1L)
                .build();
        Mockito.when(userDao.findById(user.getId())).thenReturn(Optional.of(user));
        User byUserHolder = userServiceImpl.getById(user.getId());
        Assertions.assertEquals(user, byUserHolder);
    }
}
