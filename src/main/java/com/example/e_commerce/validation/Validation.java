package com.example.e_commerce.validation;

import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.exceptions.ApiException;
import org.springframework.http.HttpStatus;

import java.util.Optional;

public class Validation {

    public static void isNull(Object data, String name){
        if(data == null){
            throw new ApiException(name + "is null.", HttpStatus.BAD_REQUEST);
        }
    }

    public static void emailAlreadyExist(String email) {

        throw new ApiException("Registration with given email:" + email + " already exist.", HttpStatus.BAD_REQUEST);
    }

    public static RuntimeException userNotExist(String email) {

        throw new ApiException( email + " not exist", HttpStatus.BAD_REQUEST);
    }

    public static void storeRegisterNullStoreData() {

        throw new ApiException("Store information must be filled in for store registration.",HttpStatus.BAD_REQUEST);
    }

    public static void isNotExist() {

        throw new ApiException("Data is null", HttpStatus.BAD_REQUEST);

    }

    public static void categoryExist(String code) {

        throw new ApiException(code + " is existing", HttpStatus.BAD_REQUEST);
    }

    public static void limitValidation(Integer limit) {
        if(limit < 1){
            throw new ApiException("Limit: " + limit + " cannot be less than one", HttpStatus.BAD_REQUEST);
        }
    }

    public static void offSetValidation(Integer offset) {
        if(offset < 0){
            throw new ApiException("Offset: " + offset + " cannot be less than zero", HttpStatus.BAD_REQUEST);
        }
    }

    public static void titleIsExist(String title) {
        throw new ApiException( title + " is exist.", HttpStatus.BAD_REQUEST);
    }

    public static void currentlyUserIsRegistered(Optional<ApplicationUser> user) {

        if(user.isEmpty()){
            throw new ApiException("The current user is not registered",HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static RuntimeException isAddressExist(Long addressId) {
        throw new ApiException("Address not exist. ID: " + addressId, HttpStatus.BAD_REQUEST);
    }

    public static RuntimeException cardIsExist(String cardName) {
        throw new ApiException("Card details not exist with name: " + cardName, HttpStatus.BAD_REQUEST);
    }

    public static RuntimeException productIsNotExist(Long id) {
        throw new ApiException("Product not exist! ID: " + id, HttpStatus.BAD_REQUEST);
    }
}
