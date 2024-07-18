package com.example.e_commerce.entity.productManagementEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name="image",schema="ecommerce")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Image {

    @NotEmpty
    private String url;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long index;

    @ManyToOne
    @JoinColumn(name="product_id")
    private Product productId;

}
