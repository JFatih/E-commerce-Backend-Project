package com.example.e_commerce.service.securityService;

import com.example.e_commerce.dto.securityDto.RegisterUser;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.Role;
import com.example.e_commerce.entity.user.RoleCode;
import com.example.e_commerce.entity.user.Store;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.exceptions.StoreDataExistValidationException;
import com.example.e_commerce.repository.securityRepository.UserRepository;
import com.example.e_commerce.validation.Validation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoreService storeService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public ApplicationUser register(RegisterUser registerUser){

        Optional<ApplicationUser> isExistUser = userRepository.findUserByEmail(registerUser.getEmail());
        ApplicationUser currentlyUser = null;
        boolean isAddRole = false;

        //user exist and exist user has request role && exist user haven't role but password is correct
        if(isExistUser.isPresent() ){
            currentlyUser = isExistUser.get();
            if(currentlyUser.getAuthorities().contains(roleService.findById(Long.valueOf(registerUser.getRole_id())))){
                throw new ApiException("Username: " + registerUser.getEmail() + ". Roles: " + roleService.findById(Long.valueOf(registerUser.getRole_id())).getName() + " are exist." , HttpStatus.BAD_REQUEST);
            }else{
                if(!passwordEncoder.matches(registerUser.getPassword(), currentlyUser.getPassword())){
                    throw new ApiException("Exist user password wrong. Please use the existing password", HttpStatus.BAD_REQUEST);
                }else{
                    isAddRole = true;
                }
            }
        }

        // user not exist && user request has store role && is Store data not null
        if(registerUser.getRole_id().equals("2") && registerUser.getStore() == null){
            Validation.storeRegisterNullStoreData();
        }

        //role entity is empty add roles data
        if(roleService.findAll().isEmpty()){
            addRole();
        }

        //Add role
        Role newRole = roleService.findById(Long.valueOf(registerUser.getRole_id()));

        //Exist user add role Or Register new user
        if(isAddRole){
            currentlyUser.addAuthority(newRole);
            return currentlyUser;
        }else{
            String encodePassword = passwordEncoder.encode(registerUser.getPassword());

            ApplicationUser user = new ApplicationUser();
            user.setName(registerUser.getName());
            user.setEmail(registerUser.getEmail());
            user.setPassword(encodePassword);
            user.addAuthority(newRole);

            if( registerUser.getRole_id().contains("2") ){
                if( registerUser.getStore() != null){

                    List<String> errors = new ArrayList<>();

                    Store store = new Store();
                    if(storeService.findByStoreName(registerUser.getStore().getName()).isPresent()){
                        errors.add("Store name already exist");
                    }
                    store.setName(registerUser.getStore().getName());
                    if(storeService.findByPhone(registerUser.getStore().getPhone()).isPresent()){
                        errors.add("Store phone number already exist");
                    }
                    store.setPhone(registerUser.getStore().getPhone());
                    if(storeService.findByBankAccount(registerUser.getStore().getBank_account()).isPresent()){
                        errors.add("Store bank Account already exist");
                    }
                    store.setBankAccount(registerUser.getStore().getBank_account());

                    if(storeService.findByTaxNo(registerUser.getStore().getTax_no()).isPresent()){
                        errors.add("Store tax no already exist");
                    }
                    store.setTaxNo(registerUser.getStore().getTax_no());

                    if(!errors.isEmpty()){
                        throw new StoreDataExistValidationException(errors,HttpStatus.BAD_REQUEST);
                    }

                    user.setStore(store);
                }else{
                    throw new ApiException("Store register must have Store data", HttpStatus.BAD_REQUEST);
                }

            }
            return userRepository.save(user);
        }


    }

    private void addRole(){
            Role role1 = new Role();
            role1.setName("Yönetici");
            role1.setCode(RoleCode.admin);
            roleService.save(role1);
            Role role2 = new Role();
            role2.setName("Mağaza");
            role2.setCode(RoleCode.store);
            roleService.save(role2);
            Role role3 = new Role();
            role3.setName("Müşteri");
            role3.setCode(RoleCode.customer);
            roleService.save(role3);
    }

    @Override
    public Optional<ApplicationUser> findUserByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

}
