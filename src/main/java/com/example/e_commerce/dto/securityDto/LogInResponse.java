package com.example.e_commerce.dto.securityDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LogInResponse {

    private String name;

    private String email;

    private List<Long> role_id;

}
