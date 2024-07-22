package com.example.e_commerce.service.securityService;

import com.example.e_commerce.dto.UserDto.orderDto.OrderDto;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.CardDetails;
import com.example.e_commerce.entity.user.orders.Order;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

public interface UserService {

    ApplicationUser findByEmail(String email);

    List<CardDetails> findUserCardsDetails(String email);

    List<Order> findAllUserOrders(UserDetails user);

}
