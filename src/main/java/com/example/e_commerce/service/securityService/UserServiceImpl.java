package com.example.e_commerce.service.securityService;

import com.example.e_commerce.dto.UserDto.orderDto.OrderDto;
import com.example.e_commerce.dto.UserDto.orderDto.OrderProductsDto;
import com.example.e_commerce.entity.productManagementEntity.Product;
import com.example.e_commerce.entity.user.Address;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.CardDetails;
import com.example.e_commerce.entity.user.orders.Order;
import com.example.e_commerce.entity.user.orders.OrderProducts;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.securityRepository.UserRepository;
import com.example.e_commerce.service.productManagementService.ProductService;
import com.example.e_commerce.service.userService.adressService.AddressService;
import com.example.e_commerce.service.userService.cardService.CardDetailsService;
import com.example.e_commerce.service.userService.orderService.OrderService;
import com.example.e_commerce.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private OrderService orderService;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User credentials are not valid"));
    }

    @Override
    public ApplicationUser findByEmail(String email) {

        return userRepository.findByEmail(email).orElseThrow( () -> new ApiException("User not exist. Email: " + email, HttpStatus.BAD_REQUEST ));
    }

    @Override
    public List<CardDetails> findUserCardsDetails(String email) {

        return userRepository.findUserCardsDetails(email);

    }

    @Override
    public List<Order> findUserAllOrders(UserDetails user) {
        return userRepository.findUserAllOrders(user.getUsername());
    }


}
