package com.example.e_commerce.dto.securityDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterStore{

    private String name;
    private String phone;
    private String tax_no;
    private String bank_account;
}
