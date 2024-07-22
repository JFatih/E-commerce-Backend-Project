package com.example.e_commerce.service.userService.adressService;

import com.example.e_commerce.dto.UserDto.AddressRequest;
import com.example.e_commerce.entity.user.Address;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;


public interface AddressService {

    Address save(AddressRequest address, String username);

    List<Address> findByUserId(UserDetails u);

    Address findById(Long id);

}
