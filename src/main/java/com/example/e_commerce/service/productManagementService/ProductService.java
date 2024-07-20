package com.example.e_commerce.service.productManagementService;

import com.example.e_commerce.dto.ProductManagementDto.ProductRequestDto;
import com.example.e_commerce.dto.ProductManagementDto.ProductResponseDto;
import com.example.e_commerce.entity.productManagementEntity.Product;

import java.util.List;
import java.util.Optional;


public interface ProductService {

    Product save(ProductRequestDto p, String username);

    List<Product> save(List<ProductRequestDto> products, String username);

    List<Product> findByParameter(Long id, String word, String sort, Integer limit, Integer offset);
}
