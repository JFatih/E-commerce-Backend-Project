package com.example.e_commerce.dto.ProductManagementDto;

import com.example.e_commerce.entity.productManagementEntity.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryListResponseDto {

    private String message;

    private List<Category> category;

}
