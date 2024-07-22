package com.example.e_commerce.repository.userRepository;

import com.example.e_commerce.entity.user.orders.OrderProducts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderProductsRepository extends JpaRepository<OrderProducts, Long> {
}
