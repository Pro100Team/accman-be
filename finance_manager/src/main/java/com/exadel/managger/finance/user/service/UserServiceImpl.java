package com.exadel.managger.finance.user.service;

import com.exadel.managger.finance.exception.user.UserNotFoundException;
import com.exadel.managger.finance.security.service.UserHolder;
import com.exadel.managger.finance.user.dao.UserDao;
import com.exadel.managger.finance.user.model.entity.User;
import com.exadel.managger.finance.user.service.api.UserService;
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
