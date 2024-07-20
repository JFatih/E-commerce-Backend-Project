package com.example.e_commerce.repository.securityRepository;

import com.example.e_commerce.entity.user.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query("SELECT a FROM ApplicationUser AS a WHERE a.email= :email")
    Optional<ApplicationUser> findUserByEmail(String email);

    @Query("SELECT a FROM ApplicationUser AS a WHERE a.email= :email")
    Optional<ApplicationUser> findByEmail(String email);
}
