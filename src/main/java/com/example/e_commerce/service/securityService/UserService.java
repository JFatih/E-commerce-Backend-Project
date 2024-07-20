package com.example.e_commerce.service.securityService;

import com.example.e_commerce.entity.user.ApplicationUser;

import java.util.Optional;

public interface UserService {

    Optional<ApplicationUser> findByEmail(String email);
}
