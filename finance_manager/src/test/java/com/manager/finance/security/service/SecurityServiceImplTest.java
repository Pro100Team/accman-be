package com.manager.finance.security.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.model.entity.api.Role;
import com.manager.finance.user.service.api.UserService;
import com.sandbox.model.UserLoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import wiremock.org.apache.commons.lang3.reflect.FieldUtils;

@ExtendWith(MockitoExtension.class)
public class SecurityServiceImplTest {
    @Mock
    private UserService userService;
    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private SecurityServiceImpl service;

    @Test
    public void getJwtToken() {
        String jwtKeyToCheck = "AAAMFwwDQYJKoZIhvcNAQEBBQADSwAwSAJ"
                + "BAIGVb6q9AbN3c0D7FgddbDM1uYnwja9cYHnXIOfTRLkUfVwxZK9mU";
        try {
            FieldUtils.writeField(service, "secretKey", jwtKeyToCheck,
                    true);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        User user = User.builder().id(1L).login("testUserName")
                .password("$2a$10$hfcHOgaLGofZz610v40JDufM.runkvGKQY7VPWBFc06dpPD0Z24uS")
                .role(Role.ROLE_USER).build();
        UserLoginDto userLoginDto = new UserLoginDto();
        userLoginDto.setPassword("testPassword");
        userLoginDto.setUsername("testUserName");
        Mockito.when(passwordEncoder.matches(userLoginDto.getPassword(),user.getPassword()))
                .thenReturn(true);
        Mockito.when(userService.getByLogin("testUserName")).thenReturn(user);
        String jwtToken = service.getJwtToken(userLoginDto);
        Claims claims =
                Jwts.parser().setSigningKey(jwtKeyToCheck.getBytes())
                        .parseClaimsJws(jwtToken.replace("Bearer ", ""))
                        .getBody();
        List<?> roles = (List<?>) claims.get("roles");
        assertAll(
                () -> assertNotNull(jwtToken),
                () -> assertEquals(user.getRole().name(), roles.get(0)),
                () -> assertEquals(userLoginDto.getUsername(), claims.get("username"))
        );
    }
}
