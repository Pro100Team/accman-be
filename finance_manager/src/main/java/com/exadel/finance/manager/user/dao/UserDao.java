package com.exadel.finance.manager.user.dao;

import com.exadel.finance.manager.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
}
