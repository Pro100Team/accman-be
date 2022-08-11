package com.manager.finance.user.service;

import com.manager.finance.exception.user.UserNotFoundException;
import com.manager.finance.security.service.UserHolder;
import com.manager.finance.user.dao.UserDao;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.service.api.UserService;
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
        String login = userHolder.getAuthentication().getName();
        return userDao.findUserByLogin(login);
    }

    public User getById(Long id) {
        return userDao.findById(id).orElseThrow(() -> new UserNotFoundException(""));
    }
}
