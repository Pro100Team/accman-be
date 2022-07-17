package com.exadel.finance.manager.service;

import com.exadel.finance.manager.model.User;
import java.util.List;

public interface UserService {
    User save(User user);

    User findById(Long id);

    User findByEmail(String email);

    List<User> findAll();

    void deleteById(Long id);
}
