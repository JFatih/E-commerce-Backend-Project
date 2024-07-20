package com.example.e_commerce.dto.ProductManagementDto;

import com.example.e_commerce.entity.productManagementEntity.Image;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductResponseDto {

    private Long id;

    private String name;

    private String description;

    private double price;

    private Long store_id;

    private Long category_id;

    private double rating;

    private int sell_count;

    private List<ImageResponseDto> images;
}
