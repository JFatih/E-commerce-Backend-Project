package com.example.e_commerce.mapper;


import com.example.e_commerce.dto.UserDto.CardDetailsRequest;
import com.example.e_commerce.dto.UserDto.CardDetailsResponse;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.CardDetails;

import java.util.ArrayList;
import java.util.List;

public class CardDetailsMapper {

    public static CardDetails cardRequestToCard(CardDetailsRequest c, ApplicationUser a){

        CardDetails newCard = new CardDetails();

        newCard.setApplicationUser(a);
        newCard.setCardNo(c.getCardNo());
        newCard.setExpireMonth(c.getExpireMonth());
        newCard.setExpireYear(c.getExpireYear());
        newCard.setNameOnCard(c.getNameOnCard());

        return newCard;

    }

    public static CardDetails cardRequestToCard(CardDetailsRequest c){

        CardDetails newCard = new CardDetails();
        newCard.setId(c.getId());
        newCard.setCardNo(c.getCardNo());
        newCard.setExpireMonth(c.getExpireMonth());
        newCard.setExpireYear(c.getExpireYear());
        newCard.setNameOnCard(c.getNameOnCard());

        return newCard;

    }

    public static CardDetailsResponse cardDetailsToCardResponse(CardDetails c){

        CardDetailsResponse resCard = new CardDetailsResponse(c.getId(),c.getApplicationUser().getId(),c.getCardNo(),c.getExpireMonth(),c.getExpireYear(),c.getNameOnCard());

        return resCard;
    }

    public static List<CardDetailsResponse> cardDetailsListToCardDetailsResponse(List<CardDetails> allCardDetails) {

        List<CardDetailsResponse> responses = new ArrayList<>();

        for(CardDetails c: allCardDetails){
            CardDetailsResponse a = cardDetailsToCardResponse(c);
            responses.add(a);
        }

        return responses;
    }
}
