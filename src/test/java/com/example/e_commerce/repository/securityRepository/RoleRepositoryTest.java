package com.example.e_commerce.repository.securityRepository;

import com.example.e_commerce.entity.user.Role;
import com.example.e_commerce.entity.user.RoleCode;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RoleRepositoryTest {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleRepositoryTest(RoleRepository roleRepository){
        this.roleRepository = roleRepository;
    }

    @BeforeEach
    void setUp() {
        Role newRole = new Role();
        newRole.setName("Test");
        newRole.setCode(RoleCode.testCode);
        roleRepository.save(newRole);
    }

    @AfterEach
    void tearDown() {
        List<Role> foundRoles = roleRepository.findAll();
        Role foundRole = foundRoles.stream().filter( (a) -> a.getName().equals("Test")).findFirst().orElse(null);
        if(foundRole != null){
            roleRepository.delete(foundRole);
        }
    }

    @Test
    void findByAuthority() {
        Role savedRole = roleRepository.findByAuthority(RoleCode.testCode).get();

        assertNotNull(savedRole);
        assertEquals("Test", savedRole.getName());
        assertEquals(RoleCode.testCode, savedRole.getCode());
    }
}