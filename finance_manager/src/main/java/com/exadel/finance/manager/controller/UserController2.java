/**
 * @Autor GapSerg
 * @Create 2022-08-01.08.2022 21:08
 **/

package com.exadel.finance.manager.controller;

import com.sandbox.api.UsersApi;
import com.sandbox.model.UserLoginDto;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class UserController2 implements UsersApi {
    @Override
    public ResponseEntity<Void> login(@Valid UserLoginDto userLoginDto) {
        return null;
    }
}
