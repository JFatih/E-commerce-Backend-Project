package com.example.e_commerce.dto.UserDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.Year;
import java.time.YearMonth;
import java.util.Calendar;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDetailsResponse {

    private Long id;

    @JsonProperty("app_user_id")
    private Long applicationUserId;

    @JsonProperty("card_no")
    private String cardNo;

    @JsonProperty("expire_month")
    private Integer expireMonth;

    @JsonProperty("expire_year")
    private Integer expireYear;

    @JsonProperty("name_on_card")
    private String nameOnCard;


}
