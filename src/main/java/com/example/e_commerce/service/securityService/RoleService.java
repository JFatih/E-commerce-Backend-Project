package com.example.e_commerce.service.securityService;

import com.example.e_commerce.entity.user.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role save(Role role);
}
