package com.example.e_commerce.service.userService;

import com.example.e_commerce.dto.UserDto.CardDetailsRequest;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.CardDetails;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.mapper.CardDetailsMapper;
import com.example.e_commerce.repository.userRepository.CardDetailsRepository;
import com.example.e_commerce.service.securityService.UserService;
import com.example.e_commerce.validation.Validation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardDetailsServiceImpl implements CardDetailsService{

    @Autowired
    private UserService userService;

    @Autowired
    private CardDetailsRepository cardDetailsRepository;

    public List<CardDetails> findUserCardsDetails(UserDetails user){

        return userService.findUserCardsDetails(user.getUsername());
    }

    @Override
    public CardDetails save(CardDetailsRequest cardDetailsRequest, UserDetails user) {

        Optional<ApplicationUser> currentlyUser = userService.findByEmail(user.getUsername());

        Validation.currentlyUserIsRegistered(currentlyUser);

        CardDetails saveCard = CardDetailsMapper.cardRequestToCard(cardDetailsRequest,currentlyUser.get());

        return cardDetailsRepository.save(saveCard);
    }

    @Override
    public CardDetails update(CardDetailsRequest card) {

        CardDetails foundCard = cardDetailsRepository.findById(card.getId()).orElseThrow( () -> new ApiException("Card not found", HttpStatus.BAD_REQUEST));

        foundCard.setCardNo(card.getCardNo());
        foundCard.setExpireMonth(card.getExpireMonth());
        foundCard.setExpireYear(card.getExpireYear());
        foundCard.setNameOnCard(card.getNameOnCard());

        return cardDetailsRepository.save(foundCard);
    }

    @Override
    public CardDetails delete(Long id) {

        CardDetails deleteCard = cardDetailsRepository.findById(id).orElseThrow( () -> new ApiException("Card not Found", HttpStatus.BAD_REQUEST));

        cardDetailsRepository.delete(deleteCard);

        return deleteCard;
    }

}
