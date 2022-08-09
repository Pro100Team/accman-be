package com.manager.finance.security.service;/* created by Kaminskii Ivan
 */

import com.manager.finance.exception.user.UserNotFoundException;
import com.manager.finance.security.service.api.SecurityService;
import com.manager.finance.user.model.entity.User;
import com.manager.finance.user.service.api.UserService;
import com.sandbox.model.UserLoginDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Value("${jwt.key}")
    private String secretKey;

    @Override
    public String getJwtToken(UserLoginDto userDto) {
        final String login = userDto.getUsername();
        User user = userService.getByLogin(login);
        if (user == null || passwordEncoder.matches(user.getPassword(), userDto.getPassword())) {
            throw new UserNotFoundException("Incorrect username or password entered");
        }
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList(user.getRole().name());
        String token = Jwts
                .builder()
                .setId(UUID.randomUUID().toString())
                .setSubject(login)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();
        return "Bearer " + token;
    }

    @Override
    public Claims validateToken(HttpServletRequest request) {
        String prefix = "Bearer ";
        String header = "Authorization";
        String jwtToken = request.getHeader(header).replace(prefix, "");
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
    }
}
