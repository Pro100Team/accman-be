package com.exadel.finance.manager.user.model.entity;

import com.exadel.finance.manager.user.model.entity.api.Role;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "users")
@Data
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "u_id", nullable = false)
    private Long id;
    @Column(name = "u_email", nullable = false, unique = true)
    private String login;
    @Column(name = "u_password", nullable = false)
    private String password;
    @Column(name = "u_role", nullable = false)
    private Role role;

}