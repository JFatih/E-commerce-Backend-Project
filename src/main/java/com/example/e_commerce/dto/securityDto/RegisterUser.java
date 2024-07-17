package com.example.e_commerce.dto.securityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RegisterUser{

    private String name;
    private String email;
    private String password;
    private Long role_id;
    private RegisterStore store;

    public RegisterUser(String name, String email, String password, Long role_id) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.role_id = role_id;
    }
}
