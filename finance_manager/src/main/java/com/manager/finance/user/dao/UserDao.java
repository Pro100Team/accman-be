package com.manager.finance.user.dao;

import com.manager.finance.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;


public interface UserDao extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
}
