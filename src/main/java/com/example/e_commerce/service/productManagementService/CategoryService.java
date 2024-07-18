package com.example.e_commerce.service.productManagementService;

import com.example.e_commerce.dto.ProductManagementDto.CategoryRequestDto;
import com.example.e_commerce.entity.productManagementEntity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    void save(List<CategoryRequestDto> categories);

    Optional<Category> findByCode(String code);
}
