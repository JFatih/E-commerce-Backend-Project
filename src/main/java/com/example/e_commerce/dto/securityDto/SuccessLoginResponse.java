package com.example.e_commerce.dto.securityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SuccessLoginResponse {

    private String email;
    private String roles;

}
