package com.example.e_commerce.dto.UserDto;

import com.example.e_commerce.entity.user.ApplicationUser;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class AddressRequest {

    @NotEmpty(message = "Title cannot be empty")
    private String title;

    @NotEmpty(message = "Name cannot be empty")
    private String name;

    @NotEmpty(message = "Surname cannot be empty")
    private String surname;

    @NotEmpty
    @Size(min=11,max = 11, message = "Phone number have 11 digit")
    private String phone;

    @NotEmpty(message = "City cannot be empty")
    private String city;

    @NotEmpty(message = "District cannot be empty")
    private String district;

    @NotEmpty(message = "Neighborhood cannot be empty")
    private String neighborhood;

    @NotEmpty(message = "Address cannot be empty")
    private String address;

}
