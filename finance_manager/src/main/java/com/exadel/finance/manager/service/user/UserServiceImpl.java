package com.exadel.finance.manager.service.user;

import static com.exadel.finance.manager.model.UserRoleName.USER;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.repository.UserRepository;
import com.exadel.finance.manager.service.AbstractEntityService;
import com.exadel.finance.manager.service.RoleService;
import com.exadel.finance.manager.service.UserService;
import java.util.Set;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl extends AbstractEntityService<User> implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           RoleService roleService) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleService = roleService;
    }

    @Override
    public User saveOrUpdate(User user) {
        log.debug("save() is called for {}", user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        setDefaultUserRoleForNewUser(user);
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        log.debug("findByEmail() is called for {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                String.format("Unable to locate email: %S in database", email)));
    }

    private void setDefaultUserRoleForNewUser(User user) {
        if (user.getRoles() == null) {
            user.setRoles(Set.of(roleService.findByRoleName(USER)));
        }
    }
}
