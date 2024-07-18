package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.securityDto.RolesResponse;
import com.example.e_commerce.entity.user.Role;

import java.util.ArrayList;
import java.util.List;

public class RolesMapper {

    public static List<RolesResponse> RoleToRolesResponse(List<Role> roles){

        List<RolesResponse> res = new ArrayList<>();

        for(Role ro : roles){
            res.add(new RolesResponse(ro.getId(),ro.getName(),ro.getCode().name()));
        }

        return res;
    }
}
