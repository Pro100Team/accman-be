package com.exadel.finance.manager.controller;

import com.sandbox.api.UsersApi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping
    public ResponseEntity<String> getHelloExadel() {
        return ResponseEntity.ok("Hello Exadel!");
    }
}
