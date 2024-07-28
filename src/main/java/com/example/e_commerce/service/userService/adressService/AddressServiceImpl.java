package com.example.e_commerce.service.userService.adressService;

import com.example.e_commerce.dto.UserDto.AddressRequest;
import com.example.e_commerce.entity.user.Address;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.mapper.AddressMapper;
import com.example.e_commerce.repository.userRepository.AddressRepository;
import com.example.e_commerce.service.securityService.UserService;
import com.example.e_commerce.validation.Validation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AddressServiceImpl implements AddressService{

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserService userService;

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    @Override
    public List<Address> findAddressByUser(UserDetails u) {

        Long userId = userService.findByEmail(u.getUsername()).getId();

        return addressRepository.findAddressByUser(userId);
    }


    @Override
    @Transactional
    public Address save(AddressRequest address, String email) {

        ApplicationUser user = userService.findByEmail(email);

        if(addressRepository.titleIsExist(address.getTitle()).isPresent()){
            Validation.titleIsExist(address.getTitle());
        }

        Address newAddress = AddressMapper.addressRequestToAddress(address,user);

        return addressRepository.save(newAddress);
    }




}
