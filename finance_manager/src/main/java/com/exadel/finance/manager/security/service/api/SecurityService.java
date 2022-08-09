package com.exadel.finance.manager.security.service.api;

import com.sandbox.model.UserLoginDto;
import io.jsonwebtoken.Claims;
import javax.servlet.http.HttpServletRequest;

public interface SecurityService {
    String getJwtToken(UserLoginDto userDto);

    Claims validateToken(HttpServletRequest request);
}
