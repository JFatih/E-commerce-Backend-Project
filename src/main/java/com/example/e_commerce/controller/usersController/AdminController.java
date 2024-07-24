package com.example.e_commerce.controller.usersController;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping
    public String successLogIn(@AuthenticationPrincipal UserDetails user){
        return "Authenticated user: " + user.getUsername();
    }

}
