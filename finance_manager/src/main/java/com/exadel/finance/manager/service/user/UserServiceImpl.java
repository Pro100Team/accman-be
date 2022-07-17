package com.exadel.finance.manager.service.user;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.repository.UserRepository;
import com.exadel.finance.manager.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.hibernate.ObjectNotFoundException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User save(User user) {
        log.debug("save() is called for {}", user.toString());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @SneakyThrows
    @Override
    public User findById(Long id) {
        log.debug("findById() is called for {}", id);
        return userRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(id, "Unable to locate User in database"));
    }

    @Override
    public User findByEmail(String email) {
        log.debug("findByEmail() is called for {}", email);
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                String.format("Unable to locate email: %S in database", email)));
    }

    @Override
    public List<User> findAll() {
        log.debug("findAll() is called");
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleteById() is called for {}", id);
        userRepository.deleteById(id);
    }
}
