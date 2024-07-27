package com.example.e_commerce.dto.securityDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterUser{

    @NotEmpty(message="name cannot empty or null")
    @Size(min=3, message = "name size have to min 3 cha.")
    private String name;

    @NotEmpty(message="email cannot empty or null")
    @Email(message="Wrong e-mail format")
    private String email;

    @NotEmpty(message="password cannot empty or null")
    @Size(min=8, message = "name size have to min 8 cha.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=]).{8,}$",
            message = "Password must be at least 8 characters long, contain at least one uppercase and one lowercase letter, one digit, one special character, and must not contain any spaces or tabs."
    )
    private String password;

    @NotEmpty(message="role_id cannot empty or null")
    private String role_id;

    @Valid
    private RegisterStore store;


    public RegisterUser(String name, String email, String password, String role_id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
    }
}
