package com.example.e_commerce.controller.examples;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String successLogIn(@AuthenticationPrincipal UserDetails userDetails){
        return "Authenticated user: " + userDetails.getUsername();
    }
}
