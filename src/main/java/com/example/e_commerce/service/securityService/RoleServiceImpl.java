package com.example.e_commerce.service.securityService;

import com.example.e_commerce.entity.user.Role;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.securityRepository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Override
    public Role findById(Long id) {
        return roleRepository.findById(id).orElseThrow( () -> new ApiException("The role with id: " + id + " is not exist.", HttpStatus.BAD_REQUEST));
    }

    @Override
    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
