package com.example.e_commerce.repository.productManagementRepository;

import com.example.e_commerce.entity.productManagementEntity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    @Query("Select c FROM Category c Where c.code = :code")
    Optional<Category> findByCode(String code);

    @Query("Select c FROM Category c WHERE c.id = :id")
    Optional<Category> findDataById(Long id);

}
