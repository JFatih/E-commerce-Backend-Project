package com.example.e_commerce.dto.securityDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RegisterStore{

    @NotEmpty(message="name cannot empty or null")
    @Size(min=3, message = "name size have to min 3 cha.")
    private String name;

    @NotEmpty(message="phone cannot empty or null")
    @Size(min=11,max = 11, message = "Phone number have 11 digit")
    private String phone;


    @NotEmpty(message="Tax no cannot empty or null")
    @Pattern(regexp = "T\\d\\d\\d\\dV\\d\\d\\d\\d\\d\\d",
            message = "Tax no format must match 'TXXXXVXXXXXX' (X is Digit)")
    private String tax_no;

    @NotEmpty(message="Bank account number cannot empty or null")
    @Size(min=16,max = 16, message = "Bank account number have to 16 digit.")
    private String bank_account;
}
