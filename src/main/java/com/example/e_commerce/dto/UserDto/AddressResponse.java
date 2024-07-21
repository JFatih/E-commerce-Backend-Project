package com.example.e_commerce.dto.UserDto;

import com.example.e_commerce.entity.user.ApplicationUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AddressResponse {

    private Long id;

    private Long user_id;

    private String title;

    private String name;

    private String surname;

    private String phone;

    private String city;

    private String district;

    private String neighborhood;

    private String address;
}
