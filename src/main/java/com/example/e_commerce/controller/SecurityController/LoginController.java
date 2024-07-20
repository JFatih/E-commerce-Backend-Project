package com.example.e_commerce.controller.SecurityController;

import com.example.e_commerce.dto.securityDto.LogInResponse;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.service.securityService.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private UserService userService;

    @PostMapping
    public String successLogIn(@AuthenticationPrincipal UserDetails userDetails){
        return "Authenticated user: " + userDetails.getUsername();
    }

}
