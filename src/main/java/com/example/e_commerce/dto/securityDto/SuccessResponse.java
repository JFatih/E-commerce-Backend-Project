package com.example.e_commerce.dto.securityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessResponse {

    private final String message = "User created.";

    private String name;

    private String email;

    private List<String> roles;

}
