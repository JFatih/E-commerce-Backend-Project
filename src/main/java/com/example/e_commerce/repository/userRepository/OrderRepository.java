package com.example.e_commerce.repository.userRepository;

import com.example.e_commerce.entity.user.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
