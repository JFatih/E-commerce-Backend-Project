package com.example.e_commerce.dto.ProductManagementDto;

import com.example.e_commerce.entity.productManagementEntity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryResponseDto {

    private String message;

    private Category category;
}
