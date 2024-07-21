package com.example.e_commerce.service.securityService;

import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.CardDetails;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.securityRepository.UserRepository;
import com.example.e_commerce.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException("User credentials are not valid"));
    }

    @Override
    public Optional<ApplicationUser> findByEmail(String email) {

        Optional<ApplicationUser> currentlyUser = userRepository.findByEmail(email);

        Validation.currentlyUserIsRegistered(currentlyUser);

        return currentlyUser;
    }

    @Override
    public List<CardDetails> findUserCardsDetails(String email) {

        return userRepository.findUserCardsDetails(email);

    }

}
