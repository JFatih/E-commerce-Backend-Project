package com.example.e_commerce.dto.securityDto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuccessResponseWStore extends SuccessResponse{

    private String store;

    public SuccessResponseWStore(String name, String email, String store) {
        super(name, email);
        this.store = store;
    }
}
