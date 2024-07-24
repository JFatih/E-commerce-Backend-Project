package com.example.e_commerce.controller.ProductManagementController;

import com.example.e_commerce.dto.ProductManagementDto.CategoryListResponseDto;
import com.example.e_commerce.dto.ProductManagementDto.CategoryRequestDto;
import com.example.e_commerce.dto.ProductManagementDto.CategoryResponseDto;
import com.example.e_commerce.entity.productManagementEntity.Category;
import com.example.e_commerce.service.productManagementService.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> findAll(){
        return categoryService.findAll();
    }

    @PostMapping("/add")
    public ResponseEntity<CategoryResponseDto> save(@RequestBody @Valid CategoryRequestDto newCategory){

        Category savedCategory = categoryService.save(newCategory);

        return new ResponseEntity<>(new CategoryResponseDto("Category saved!", savedCategory), HttpStatus.CREATED);
    }

    @PostMapping("/adds")
    public ResponseEntity<CategoryListResponseDto> saveAll(@RequestBody List<@Valid CategoryRequestDto> categories){

        List<Category> savedCategory = categoryService.saveAll(categories);

        return new ResponseEntity<>(new CategoryListResponseDto("All category saved!",savedCategory), HttpStatus.CREATED);
    }
}
