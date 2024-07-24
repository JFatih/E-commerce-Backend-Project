package com.example.e_commerce.service.productManagementService;

import com.example.e_commerce.dto.ProductManagementDto.CategoryRequestDto;
import com.example.e_commerce.entity.productManagementEntity.Category;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    List<Category> findAll();

    List<Category> saveAll(List<CategoryRequestDto> categories);

    Category save(CategoryRequestDto categories);

    Category findByCode(String code);

    Category findById(Long id);


}
