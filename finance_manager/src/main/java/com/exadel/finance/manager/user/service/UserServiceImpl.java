package com.exadel.finance.manager.user.service;

import com.exadel.finance.manager.exception.user.UserNotFoundException;
import com.exadel.finance.manager.security.service.UserHolder;
import com.exadel.finance.manager.user.dao.UserDao;
import com.exadel.finance.manager.user.model.entity.User;
import com.exadel.finance.manager.user.service.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    private final UserHolder userHolder;

    @Override
    public User getByLogin(String login) {
        return userDao.findUserByLogin(login);
    }

    @Override
    public User getByUserHolder() {
        return userDao.findUserByLogin(userHolder.getAuthentication().getName());
    }

    public User getById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new UserNotFoundException(""));
    }
}
