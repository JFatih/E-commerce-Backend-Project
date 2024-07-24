package com.example.e_commerce.controller.usersController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/store")
public class StoreController {

    @GetMapping
    public String welcome(){
        return "Store area";
    }
}
