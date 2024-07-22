package com.example.e_commerce.repository.userRepository;
import com.example.e_commerce.entity.user.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface CardDetailsRepository extends JpaRepository<CardDetails,Long> {

    @Query("SELECT c FROM CardDetails c WHERE c.cardNo = :cardNo AND c.nameOnCard = :cardName AND c.expireMonth = :expireMonth AND c.expireYear = :expireYear")
    Optional<CardDetails> findByParameters(String cardNo, String cardName, Integer expireMonth, Integer expireYear);
}
