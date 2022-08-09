package com.exadel.managger.finance.user.controller;

import com.exadel.managger.finance.security.service.api.SecurityService;
import com.sandbox.api.UsersApi;
import com.sandbox.model.UserLoginDto;
import com.sandbox.model.UserResponseDto;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class UserController implements UsersApi {
    private final SecurityService securityService;

    @Override
    public ResponseEntity<Void> deleteUserById(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<UserResponseDto> getUserById(Long userId) {
        return null;
    }

    @Override
    public ResponseEntity<Void> login(@Valid UserLoginDto userLoginDto) {
        String jwtToken = securityService.getJwtToken(userLoginDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", jwtToken);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> logout() {

        return null;
    }
}
