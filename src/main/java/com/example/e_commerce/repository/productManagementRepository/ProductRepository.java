package com.example.e_commerce.repository.productManagementRepository;

import com.example.e_commerce.entity.productManagementEntity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Optional<Product> findByProductName(String name);

    @Query("SELECT p FROM Product p WHERE p.price = :price")
    Optional<Product> findByProductPrice(double price);

}
