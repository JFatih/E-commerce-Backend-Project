package com.example.e_commerce.controller.SecurityController;

import com.example.e_commerce.dto.securityDto.RolesResponse;
import com.example.e_commerce.mapper.RolesMapper;
import com.example.e_commerce.repository.securityRepository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RolesController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<RolesResponse> findAll(){
        return RolesMapper.RoleToRolesResponse(roleRepository.findAll());
    }
}
