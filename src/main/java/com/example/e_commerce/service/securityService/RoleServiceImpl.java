package com.example.e_commerce.service.securityService;

import com.example.e_commerce.entity.user.Role;
import com.example.e_commerce.repository.securityRepository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
