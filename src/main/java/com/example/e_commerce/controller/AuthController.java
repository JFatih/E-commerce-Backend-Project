package com.example.e_commerce.controller;

import com.example.e_commerce.dto.securityDto.RegisterUser;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.service.securityService.AuthenticationService;
import com.example.e_commerce.service.securityService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/signup")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping
    public ApplicationUser register(@Valid @RequestBody RegisterUser registerUser){
        return authenticationService.register(registerUser);
    }

}
