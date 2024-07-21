package com.example.e_commerce.repository.userRepository;

import com.example.e_commerce.entity.user.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.title = :title")
    Optional<Address> titleIsExist(String title);

    @Query("SELECT a FROM Address a WHERE a.applicationUser.id = :id")
    List<Address> findAddressByUser(Long id);
}
