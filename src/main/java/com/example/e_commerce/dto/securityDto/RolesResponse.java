package com.example.e_commerce.dto.securityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesResponse {
    private Long id;
    private String name;
    private String code;
}
