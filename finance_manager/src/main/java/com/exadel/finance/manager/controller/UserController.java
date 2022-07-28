/**
 * @Autor GapSerg
 * @Create 2022-07-28.07.2022 13:08
 **/

package com.exadel.finance.manager.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.Optional;
import org.example.api.UserApi;
import org.example.model.User;
@Controller
public class UserController implements UserApi {


    @Override
    public ResponseEntity<String> loginUser(User user) {
          return ResponseEntity.ok("!!! "+user.getLogin());

    }
}

