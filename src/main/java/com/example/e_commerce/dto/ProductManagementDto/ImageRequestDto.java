package com.example.e_commerce.dto.ProductManagementDto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ImageRequestDto {

    @NotEmpty
    private String url;
}
