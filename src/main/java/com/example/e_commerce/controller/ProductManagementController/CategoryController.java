package com.example.e_commerce.controller.ProductManagementController;

import com.example.e_commerce.dto.ProductManagementDto.CategoryRequestDto;
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

    // TODO "bu postu admin tanımla"
    @PostMapping("/add")
    public ResponseEntity<String> save(@RequestBody List<@Valid CategoryRequestDto> categories){

        categoryService.save(categories);

        return new ResponseEntity<>("All category saved!", HttpStatus.CREATED);
    }
}
