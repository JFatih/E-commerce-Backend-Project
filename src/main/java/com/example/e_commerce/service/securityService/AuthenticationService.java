package com.example.e_commerce.service.securityService;

import com.example.e_commerce.dto.securityDto.RegisterUser;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.Role;
import com.example.e_commerce.entity.user.RoleCode;
import com.example.e_commerce.entity.user.Store;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.securityRepository.RoleRepository;
import com.example.e_commerce.repository.securityRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public ApplicationUser register(RegisterUser registerUser){

        if(roleRepository.findAll().isEmpty()){
            Role role1 = new Role();
            role1.setName("Yönetici");
            role1.setCode(RoleCode.admin);
            roleRepository.save(role1);
            Role role2 = new Role();
            role2.setName("Mağaza");
            role2.setCode(RoleCode.store);
            roleRepository.save(role2);
            Role role3 = new Role();
            role3.setName("Müşteri");
            role3.setCode(RoleCode.customer);
            roleRepository.save(role3);
        }

        Role role = roleRepository.findById(registerUser.getRole_id()).orElseThrow( () -> new ApiException("Invalid role Id:" + registerUser.getRole_id(), HttpStatus.BAD_REQUEST));

        String encodePassword = passwordEncoder.encode(registerUser.getPassword());

        ApplicationUser user = new ApplicationUser();
        user.setName(registerUser.getName());
        user.setEmail(registerUser.getEmail());
        user.setPassword(encodePassword);
        user.setRole(role);

        if( registerUser.getRole_id() == 2 && registerUser.getStore() != null ){
            Store store = new Store();
            store.setName(registerUser.getStore().getName());
            store.setPhone(registerUser.getStore().getPhone());
            store.setBankAccount(registerUser.getStore().getBank_account());
            store.setTaxNo(registerUser.getStore().getTax_no());
            user.setStore(store);
        }

        return userRepository.save(user);
    }
}
