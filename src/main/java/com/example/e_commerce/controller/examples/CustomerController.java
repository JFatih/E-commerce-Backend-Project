package com.example.e_commerce.controller.examples;

import com.example.e_commerce.dto.UserDto.AddressRequest;
import com.example.e_commerce.dto.UserDto.AddressResponse;
import com.example.e_commerce.dto.UserDto.CardDetailsRequest;
import com.example.e_commerce.dto.UserDto.CardDetailsResponse;
import com.example.e_commerce.dto.UserDto.orderDto.OrderDto;
import com.example.e_commerce.entity.user.Address;
import com.example.e_commerce.entity.user.CardDetails;
import com.example.e_commerce.entity.user.orders.Order;
import com.example.e_commerce.mapper.AddressMapper;
import com.example.e_commerce.mapper.CardDetailsMapper;
import com.example.e_commerce.mapper.OrderMapper;
import com.example.e_commerce.service.securityService.UserService;
import com.example.e_commerce.service.userService.adressService.AddressService;
import com.example.e_commerce.service.userService.cardService.CardDetailsService;
import com.example.e_commerce.service.userService.orderService.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    private AddressService addressService;

    @Autowired
    private CardDetailsService cardDetailsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;


    @GetMapping
    public String welcome(){
        return "Customer area";
    }

    @GetMapping("/address")
    public List<AddressResponse> findAll(@AuthenticationPrincipal UserDetails u){
        return AddressMapper.addressListToAddressResponseList(addressService.findByUserId(u));
    }

    @PostMapping("/address")
    public ResponseEntity<AddressResponse> save(@Valid @RequestBody AddressRequest addressRequest, @AuthenticationPrincipal UserDetails user){

        Address address = addressService.save(addressRequest,user.getUsername());

        AddressResponse response = AddressMapper.adressToAddressResponse(address);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/card")
    public List<CardDetailsResponse> findUserCardsDetails(@AuthenticationPrincipal UserDetails user){

        List<CardDetails> allCardDetails = cardDetailsService.findUserCardsDetails(user);

        return CardDetailsMapper.cardDetailsListToCardDetailsResponse(allCardDetails);
    }

    @PostMapping("/card")
    public ResponseEntity<CardDetailsResponse> save(@RequestBody CardDetailsRequest cardRequest, @AuthenticationPrincipal UserDetails user){

        CardDetails savedCardDetails = cardDetailsService.save(cardRequest,user);

        CardDetailsResponse response = CardDetailsMapper.cardDetailsToCardResponse(savedCardDetails);

        return new ResponseEntity<>(response,HttpStatus.CREATED);
    }

    @PutMapping("/card")
    public CardDetailsResponse updateCardDetails(@RequestBody CardDetailsRequest card){

        return CardDetailsMapper.cardDetailsToCardResponse(cardDetailsService.update(card));

    }

    @DeleteMapping("/card/{id}")
    public CardDetailsResponse deleteCardDetails(@PathVariable Long id){

        return CardDetailsMapper.cardDetailsToCardResponse(cardDetailsService.delete(id));

    }

    @GetMapping("/order")
    public List<OrderDto> findAllUserOrders(@AuthenticationPrincipal UserDetails user ){

        List<Order> userAllOrders = userService.findAllUserOrders(user);

        return OrderMapper.orderListToOrderDtoList(userAllOrders);
    }

    @PostMapping("/order")
    public ResponseEntity<OrderDto> saveOrder(@RequestBody OrderDto order, @AuthenticationPrincipal UserDetails user){

        Order savedOrder = orderService.saveOrder(order,user);

        return new ResponseEntity<>(OrderMapper.orderToOrderDto(savedOrder),HttpStatus.CREATED);

    }
}
