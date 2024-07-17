package com.example.e_commerce.repository.securityRepository;

import com.example.e_commerce.entity.user.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
