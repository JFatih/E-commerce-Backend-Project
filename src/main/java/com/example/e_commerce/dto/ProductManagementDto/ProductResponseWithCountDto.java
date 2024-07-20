package com.example.e_commerce.dto.ProductManagementDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductResponseWithCountDto {

    private int total;

    private List<ProductResponseDto> products;

}
