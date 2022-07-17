package com.exadel.finance.manager.controllers;

import static com.exadel.finance.manager.util.MapperUtil.map;
import static com.exadel.finance.manager.util.MapperUtil.mapToList;

import com.exadel.finance.manager.model.User;
import com.exadel.finance.manager.model.dto.request.UserRegistrationDto;
import com.exadel.finance.manager.model.dto.response.UserResponseDto;
import com.exadel.finance.manager.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public UserResponseDto create(@RequestBody @Valid UserRegistrationDto userDto) {
        User user = userService.save(map(userDto, User.class));
        return map(user, UserResponseDto.class);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        return ResponseEntity.ok(mapToList(userService.findAll(), UserResponseDto.class));
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable("id") Long userId) {
        return ResponseEntity.ok(map(userService.findById(userId), UserResponseDto.class));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponseDto> getUserByEmail(@PathVariable("email") String userEmail) {
        return ResponseEntity.ok(map(userService.findByEmail(userEmail), UserResponseDto.class));
    }
}
