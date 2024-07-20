package com.example.e_commerce.dto.ProductManagementDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {

    @NotEmpty
    private String name;

    @NotEmpty
    private String description;

    @NotNull
    @Min(0)
    private double price;

    @NotNull
    @Min(0)
    private int stock;

    private Long store_id;

    @NotNull
    private Long category_id;

    @NotNull
    @Min(0)
    private double rating;

    @NotNull
    @Min(0)
    private int sell_count;

    @Valid
    @NotNull
    private List<ImageRequestDto> images;
}
