package com.example.e_commerce.service.securityService;

import com.example.e_commerce.dto.securityDto.RegisterUser;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.Role;
import com.example.e_commerce.repository.securityRepository.UserRepository;

import java.util.List;
import java.util.Optional;

public interface AuthenticationService {

    ApplicationUser register(RegisterUser registerUser);

    Optional<ApplicationUser> findUserByEmail(String email);

}
