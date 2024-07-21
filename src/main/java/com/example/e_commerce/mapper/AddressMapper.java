package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.UserDto.AddressRequest;
import com.example.e_commerce.dto.UserDto.AddressResponse;
import com.example.e_commerce.entity.user.Address;
import com.example.e_commerce.entity.user.ApplicationUser;

import java.util.ArrayList;
import java.util.List;

public class AddressMapper {

    public static Address addressRequestToAddress(AddressRequest a, ApplicationUser user){

        Address res = new Address();
        res.setApplicationUser(user);
        res.setTitle(a.getTitle());
        res.setName(a.getName());
        res.setSurname(a.getSurname());
        res.setPhone(a.getPhone());
        res.setCity(a.getCity());
        res.setDistrict(a.getDistrict());
        res.setNeighborhood(a.getNeighborhood());
        res.setAddress(a.getAddress());

        return res;
    }

    public static AddressResponse adressToAddressResponse(Address a){

        AddressResponse res = new AddressResponse();
        res.setId(a.getId());
        res.setUser_id(a.getApplicationUser().getId());
        res.setTitle(a.getTitle());
        res.setName(a.getName());
        res.setSurname(a.getSurname());
        res.setPhone(a.getPhone());
        res.setCity(a.getCity());
        res.setDistrict(a.getDistrict());
        res.setNeighborhood(a.getNeighborhood());
        res.setAddress(a.getAddress());

        return res;
    }

    public static List<AddressResponse> addressListToAddressResponseList(List<Address> all) {

        List<AddressResponse> responses = new ArrayList<>();

        for(Address a: all){
            AddressResponse res = adressToAddressResponse(a);
            responses.add(res);
        }

        return responses;
    }
}
