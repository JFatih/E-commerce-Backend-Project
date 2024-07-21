package com.example.e_commerce.service.userService;

import com.example.e_commerce.dto.UserDto.AddressRequest;
import com.example.e_commerce.entity.user.Address;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.mapper.AddressMapper;
import com.example.e_commerce.repository.userRepository.AddressRepository;
import com.example.e_commerce.service.securityService.UserService;
import com.example.e_commerce.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Address> findByUserId(UserDetails u) {

        Long userId = userService.findByEmail(u.getUsername()).get().getId();

        System.out.println(userId);

        List<Address> res = addressRepository.findAddressByUser(userId);

        System.out.println(res);
        return res;
    }

    @Override
    public Address save(AddressRequest address, String email) {

        ApplicationUser user = userService.findByEmail(email).orElseThrow( () -> Validation.userNotExist(email));

        if(addressRepository.titleIsExist(address.getTitle()).isPresent()){
            Validation.titleIsExist(address.getTitle());
        }

        Address newAddress = AddressMapper.addressRequestToAddress(address,user);

        return addressRepository.save(newAddress);
    }




}
