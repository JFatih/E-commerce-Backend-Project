package com.example.e_commerce.dto.UserDto.orderDto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class OrderProductsDto {

    @JsonProperty("product_id")
    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @NotNull(message = "Count cannot be null")
    private Integer count;

    @NotEmpty(message = "Detail cannot be empty")
    private String detail;

}
