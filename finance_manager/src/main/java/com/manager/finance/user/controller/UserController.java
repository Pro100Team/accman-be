package com.manager.finance.user.controller;

import com.manager.finance.mapstruct.mapper.UserMapper;
import com.manager.finance.security.service.api.SecurityService;
import com.manager.finance.user.service.api.ProfileService;
import com.manager.finance.user.service.api.UserService;
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
    private final ProfileService profileService;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public ResponseEntity<Void> deleteUser() {
        profileService.deleteProfile(userService.getByUserHolder());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserResponseDto> getUserById(Long userId) {
        return ResponseEntity.ok(userMapper.userToDto(userService.getById(userId)));
    }

    @Override
    public ResponseEntity<Void> login(@Valid UserLoginDto userLoginDto) {
        String jwtToken = securityService.getJwtToken(userLoginDto);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", jwtToken);
        return new ResponseEntity<>(httpHeaders, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> logout(String authorization) {
        return null;
    }
}
