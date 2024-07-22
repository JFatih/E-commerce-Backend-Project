package com.example.e_commerce.service.userService.orderService;

import com.example.e_commerce.dto.UserDto.orderDto.OrderDto;
import com.example.e_commerce.dto.UserDto.orderDto.OrderProductsDto;
import com.example.e_commerce.entity.productManagementEntity.Product;
import com.example.e_commerce.entity.user.Address;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.CardDetails;
import com.example.e_commerce.entity.user.orders.Order;
import com.example.e_commerce.entity.user.orders.OrderProducts;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.userRepository.OrderRepository;
import com.example.e_commerce.service.productManagementService.ProductService;
import com.example.e_commerce.service.securityService.UserService;
import com.example.e_commerce.service.userService.adressService.AddressService;
import com.example.e_commerce.service.userService.cardService.CardDetailsService;
import com.example.e_commerce.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CardDetailsService cardDetailsService;

    @Autowired
    private ProductService productService;

    @Override
    public Order saveOrder(OrderDto order, UserDetails user) {

        ApplicationUser currentlyUser = userService.findByEmail(user.getUsername());

        Order newOrder = new Order();
        newOrder.setApplicationUser(currentlyUser);

        Address foundAddress = addressService.findById(order.getAddressId());
        newOrder.setAddress(foundAddress);

        newOrder.setOrderDate(order.getOrderDate());

        CardDetails foundCardDetails = cardDetailsService.findByParameters(String.valueOf(order.getCardNo()),order.getCardName(),order.getCardExpireMonth(),order.getCardExpireYear());
        newOrder.setCardDetails(foundCardDetails);

        newOrder.setCardCcv(order.getCardCcv());
        newOrder.setPrice(order.getPrice());

        List<OrderProducts> saveOrderProducts = new ArrayList<>();

        for(OrderProductsDto or: order.getProducts()){

            OrderProducts newOrderProducts = new OrderProducts();

            newOrderProducts.setOrder(newOrder);
            Product foundProduct = productService.findById(or.getProductId());
            newOrderProducts.setProduct(foundProduct);

            newOrderProducts.setCount(or.getCount());
            newOrderProducts.setDetail(or.getDetail());

            saveOrderProducts.add(newOrderProducts);
        }

        newOrder.setOrderProducts(saveOrderProducts);

        return orderRepository.save(newOrder);
    }
}
