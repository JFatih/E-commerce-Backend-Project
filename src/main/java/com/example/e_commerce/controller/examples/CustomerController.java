package com.example.e_commerce.controller.examples;

import com.example.e_commerce.dto.UserDto.AddressRequest;
import com.example.e_commerce.dto.UserDto.AddressResponse;
import com.example.e_commerce.dto.UserDto.CardDetailsRequest;
import com.example.e_commerce.dto.UserDto.CardDetailsResponse;
import com.example.e_commerce.entity.user.Address;
import com.example.e_commerce.entity.user.CardDetails;
import com.example.e_commerce.mapper.AddressMapper;
import com.example.e_commerce.mapper.CardDetailsMapper;
import com.example.e_commerce.service.securityService.UserService;
import com.example.e_commerce.service.userService.AddressService;
import com.example.e_commerce.service.userService.CardDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
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
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private CardDetailsService cardDetailsService;

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
}
