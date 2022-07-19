package com.exadel.finance.manager.service.user;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.repository.UserRepository;
import com.exadel.finance.manager.service.AbstractEntityService;
import com.exadel.finance.manager.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserServiceImpl extends AbstractEntityService<User> implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        super(userRepository);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User saveOrUpdate(User user) {
        log.debug("save() is called for {}", user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        log.debug("findByEmail() is called for {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                String.format("Unable to locate email: %S in database", email)));
    }
}
