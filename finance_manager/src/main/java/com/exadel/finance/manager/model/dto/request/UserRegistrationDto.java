package com.exadel.finance.manager.model.dto.request;

import com.exadel.finance.manager.validation.Password;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Password(field = "password", fieldMatch = "repeatPassword")
public class UserRegistrationDto {
    @NotBlank(message = "Login can't be blank!")
    @Size(min = 5, message = "Login must be at least 5 symbols long")
    @Email
    private String email;

    @NotBlank(message = "Password can't be blank!")
    @Size(min = 8, message = "Password must be at least 8 symbols long")
    private String password;
    private String repeatPassword;

    @NotBlank(message = "Please enter your name")
    @Size(min = 3, message = "Name must be at least 3 symbols long")
    private String name;
}
