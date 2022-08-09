package com.exadel.managger.finance.user.dao;

import com.exadel.managger.finance.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findUserByLogin(String login);
}
