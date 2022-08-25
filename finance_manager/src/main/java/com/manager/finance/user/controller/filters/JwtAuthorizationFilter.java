package com.manager.finance.user.controller.filters;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    private final String header = "Authorization";
    private final String cookie = "JWT";
    private final String prefix = "Bearer ";
    @Value("${jwt.key}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {
        try {
            if (checkJwtToken(request, response)) {
                Claims claims = validateToken(request);
                if (claims.get("roles") != null) {
                    setUpSpringAuthentication(claims);
                } else {
                    SecurityContextHolder.clearContext();
                }
            } else {
                SecurityContextHolder.clearContext();
            }
            chain.doFilter(request, response);
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.sendError(HttpServletResponse.SC_FORBIDDEN,
                    e.getMessage());
        }
    }

    private Claims validateToken(HttpServletRequest request) {
        String jwtToken = request.getHeader(header).replace(prefix, "");
        return Jwts.parser().setSigningKey(secretKey.getBytes()).parseClaimsJws(jwtToken).getBody();
    }

    private void setUpSpringAuthentication(Claims claims) {
        @SuppressWarnings("unchecked")
        List<String> authorities = (List<String>) claims.get("roles");

        UsernamePasswordAuthenticationToken auth =
                new UsernamePasswordAuthenticationToken(claims.get("username"), null,
                        authorities.stream().map(SimpleGrantedAuthority::new)
                                .collect(Collectors.toList()));
        SecurityContextHolder.getContext().setAuthentication(auth);

    }

    private boolean checkJwtToken(HttpServletRequest request, HttpServletResponse response) {
        String authenticationHeader = request.getHeader(header);
        return authenticationHeader != null && authenticationHeader.startsWith(prefix);
    }

}
