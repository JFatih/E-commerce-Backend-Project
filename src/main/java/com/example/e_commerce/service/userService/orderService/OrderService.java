package com.example.e_commerce.service.userService.orderService;

import com.example.e_commerce.dto.UserDto.orderDto.OrderDto;
import com.example.e_commerce.entity.user.orders.Order;
import com.example.e_commerce.service.securityService.UserService;
import org.springframework.security.core.userdetails.UserDetails;

public interface OrderService {

    Order saveOrder(OrderDto order, UserDetails user);
}
