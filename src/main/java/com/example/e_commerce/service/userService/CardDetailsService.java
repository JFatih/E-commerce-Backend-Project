package com.example.e_commerce.service.userService;

import com.example.e_commerce.dto.UserDto.CardDetailsRequest;
import com.example.e_commerce.entity.user.CardDetails;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CardDetailsService {

    CardDetails save(CardDetailsRequest cardDetailsRequest, UserDetails user);

    List<CardDetails> findUserCardsDetails(UserDetails user);

    CardDetails update(CardDetailsRequest card);

    CardDetails delete(Long id);

}
