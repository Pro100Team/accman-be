package com.exadel.finance.manager.service.user;

import com.exadel.finance.manager.model.Role;
import com.exadel.finance.manager.repository.RoleRepository;
import com.exadel.finance.manager.service.RoleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.hibernate.ObjectNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role save(Role role) {
        log.debug("save() is called for {}", role);
        return roleRepository.save(role);
    }

    @SneakyThrows
    @Override
    public Role findById(Long id) {
        log.debug("findById() is called for {}", id);
        return roleRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(id, "Unable to locate Role in database"));
    }

    @Override
    public List<Role> findAll() {
        log.debug("findAll() is called");
        return roleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        log.debug("deleteById() is called for {}", id);
        roleRepository.deleteById(id);
    }
}
