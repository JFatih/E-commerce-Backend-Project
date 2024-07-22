package com.example.e_commerce.dto.UserDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDetailsRequest {

    private Long id;

    @JsonProperty("card_no")
    @Size(min = 16,max = 16, message = "Card value have to 16 digit")
    @NotEmpty
    private String cardNo;

    @JsonProperty("expire_month")
    @Min(1)
    @Max(12)
    private Integer expireMonth;

    @JsonProperty("expire_year")
    @Min(2024)
    @Max(2034)
    private Integer expireYear;

    @JsonProperty("name_on_card")
    @NotEmpty
    private String nameOnCard;

}
