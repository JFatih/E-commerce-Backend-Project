package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.securityDto.SuccessResponse;
import com.example.e_commerce.dto.securityDto.SuccessResponseWStore;
import com.example.e_commerce.entity.user.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppUserMapper {

    public static SuccessResponse appUserToSuccessResponse(ApplicationUser appUser){
        List<String> roles = new ArrayList<>();
        appUser.getAuthorities().forEach( a -> roles.add(a.getAuthority()));
        if(appUser.getStore() != null ){
            return new SuccessResponseWStore(appUser.getName(), appUser.getEmail(), roles, appUser.getStore().getName());
        }else{
            return new SuccessResponse(appUser.getName(), appUser.getEmail(), roles);
        }

    }

}
