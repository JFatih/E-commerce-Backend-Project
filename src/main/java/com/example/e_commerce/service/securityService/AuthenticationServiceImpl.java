package com.example.e_commerce.service.securityService;

import com.example.e_commerce.dto.securityDto.RegisterUser;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.Role;
import com.example.e_commerce.entity.user.RoleCode;
import com.example.e_commerce.entity.user.Store;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.securityRepository.RoleRepository;
import com.example.e_commerce.repository.securityRepository.UserRepository;
import com.example.e_commerce.validation.Validation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public ApplicationUser register(RegisterUser registerUser){

        if(userRepository.findUserByEmail(registerUser.getEmail()).isPresent()){
            Validation.emailAlreadyExist(registerUser.getEmail());
        }

        if(registerUser.getRole_id().contains(3L) && registerUser.getStore() == null){
            Validation.storeRegisterNullStoreData();
        }

        if(roleRepository.findAll().isEmpty()){
            addRole();
        }

        Set<Role> roles = new HashSet<>();
        
        for(Long roleId : registerUser.getRole_id()){
            Role role = roleRepository.findById(roleId).orElseThrow( () -> new ApiException("Invalid role Id:" + roleId, HttpStatus.BAD_REQUEST));
            roles.add(role);
        }

        String encodePassword = passwordEncoder.encode(registerUser.getPassword());

        ApplicationUser user = new ApplicationUser();
        user.setName(registerUser.getName());
        user.setEmail(registerUser.getEmail());
        user.setPassword(encodePassword);
        user.setAuthorities(roles);

        if( registerUser.getRole_id().contains(2L) && registerUser.getStore() != null ){
            Store store = new Store();
            store.setName(registerUser.getStore().getName());
            store.setPhone(registerUser.getStore().getPhone());
            store.setBankAccount(registerUser.getStore().getBank_account());
            store.setTaxNo(registerUser.getStore().getTax_no());
            user.setStore(store);
        }

        return userRepository.save(user);
    }

    private void addRole(){
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

    @Override
    public ApplicationUser findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow( () -> Validation.userNotExist(email));
    }

}
