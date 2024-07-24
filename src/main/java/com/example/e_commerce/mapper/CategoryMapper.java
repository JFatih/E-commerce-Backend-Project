package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.ProductManagementDto.CategoryListResponseDto;
import com.example.e_commerce.dto.ProductManagementDto.CategoryRequestDto;
import com.example.e_commerce.dto.ProductManagementDto.CategoryResponseDto;
import com.example.e_commerce.entity.productManagementEntity.Category;

import java.util.List;

public class CategoryMapper {

    public static CategoryResponseDto categoryToResponse(Category category,String message){

        CategoryResponseDto response = new CategoryResponseDto();

        response.setMessage(message);
        response.setCategory(category);

        return response;
    }

    public static CategoryListResponseDto categoryToResponse(List<Category> categories, String message){

        CategoryListResponseDto response = new CategoryListResponseDto();

        response.setMessage(message);
        response.setCategory(categories);

        return response;
    }

}
