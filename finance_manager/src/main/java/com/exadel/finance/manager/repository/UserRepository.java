package com.exadel.finance.manager.repository;

import com.exadel.finance.manager.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("FROM User u JOIN FETCH u.roles WHERE u.id = ?1")
    Optional<User> findById(Long id);

    @Query("FROM User u JOIN FETCH u.roles WHERE u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("SELECT DISTINCT u FROM User u JOIN FETCH u.roles")
    List<User> findAll();
}
