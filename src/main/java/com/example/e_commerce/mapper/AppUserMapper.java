package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.securityDto.SuccessResponse;
import com.example.e_commerce.dto.securityDto.SuccessResponseWStore;
import com.example.e_commerce.entity.user.ApplicationUser;

public class AppUserMapper {

    public static SuccessResponse appUserToSuccessResponse(ApplicationUser appUser){

        if(appUser.getStore() != null ){
            return new SuccessResponseWStore(appUser.getName(), appUser.getEmail(), appUser.getStore().getName());
        }else{
            return new SuccessResponse(appUser.getName(), appUser.getEmail());
        }

    }

}
