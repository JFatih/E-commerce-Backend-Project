package com.example.e_commerce.repository.securityRepository;

import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.CardDetails;
import com.example.e_commerce.entity.user.orders.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<ApplicationUser, Long> {

    @Query("SELECT a FROM ApplicationUser AS a WHERE a.email= :email")
    Optional<ApplicationUser> findUserByEmail(String email);

    @Query("SELECT a.cards FROM ApplicationUser a WHERE a.email = :email")
    List<CardDetails> findUserCardsDetails(String email);

    @Query("SELECT a.orders FROM ApplicationUser a WHERE a.email = :email")
    List<Order> findUserAllOrders(String email);
}
