package com.example.e_commerce.dto.ProductManagementDto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryRequestDto{

    @NotEmpty
    private String code;

    @NotEmpty
    private String title;

    @NotEmpty
    private String img;

    @NotNull
    private double rating;

    @NotEmpty
    private String gender;

}
