package com.example.e_commerce.controller.SecurityController;
import com.example.e_commerce.dto.securityDto.RegisterUser;
import com.example.e_commerce.dto.securityDto.SuccessResponse;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.mapper.AppUserMapper;
import com.example.e_commerce.service.securityService.AuthenticationService;
import com.example.e_commerce.service.securityService.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SuccessResponse> register(@Valid @RequestBody RegisterUser registerUser){

        ApplicationUser savedUser = authenticationService.register(registerUser);

        return new ResponseEntity<>(AppUserMapper.appUserToSuccessResponse(savedUser), HttpStatus.CREATED);
    }

}
