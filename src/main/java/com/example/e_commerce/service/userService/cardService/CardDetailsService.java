package com.example.e_commerce.service.userService.cardService;

import com.example.e_commerce.dto.UserDto.CardDetailsRequest;
import com.example.e_commerce.entity.user.CardDetails;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Optional;

public interface CardDetailsService {

    CardDetails save(CardDetailsRequest cardDetailsRequest, UserDetails user);

    List<CardDetails> findUserCardsDetails(UserDetails user);

    CardDetails update(CardDetailsRequest card);

    CardDetails delete(Long id);

    CardDetails findByParameters(String cardNo,String cardName,Integer expireMonth,Integer expireYear);

}
