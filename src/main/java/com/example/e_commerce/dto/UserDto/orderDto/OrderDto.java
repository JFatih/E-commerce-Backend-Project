package com.example.e_commerce.dto.UserDto.orderDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class OrderDto {

    @JsonProperty("address_id")
    @NotNull(message = "Address ID cannot be null")
    private Long addressId;

    @JsonProperty("order_date")
    @NotEmpty(message = "Order date cannot be empty")
    private String orderDate;

    @JsonProperty("card_no")
    @NotNull(message = "Card number cannot be null")
    private BigInteger cardNo;

    @JsonProperty("card_name")
    @NotEmpty(message = "Card name cannot be empty")
    private String cardName;

    @JsonProperty("card_expire_month")
    @Min(value = 1, message = "Card expire month must be at least 1")
    @Max(value = 12, message = "Card expire month must be at most 12")
    private Integer cardExpireMonth;

    @JsonProperty("card_expire_year")
    @Min(value = 2024, message = "Card expire year must be at least 2024")
    @Max(value = 2034, message = "Card expire year must be at most 2034")
    private Integer cardExpireYear;

    @JsonProperty("card_ccv")
    @Size(min = 3, max = 3, message = "Card CCV must be exactly 3 digits")
    private Integer cardCcv;

    @Min(value = 0, message = "Price cannot be negative")
    private Integer price;

    @NotEmpty(message = "Product list cannot be empty")
    private List<OrderProductsDto> products;

}
