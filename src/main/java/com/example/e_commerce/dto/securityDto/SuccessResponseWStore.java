package com.example.e_commerce.dto.securityDto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SuccessResponseWStore extends SuccessResponse{

    private String store;

    public SuccessResponseWStore(String name, String email, List<String> roles, String store) {
        super(name, email, roles);
        this.store = store;
    }
}
