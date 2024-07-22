package com.example.e_commerce.entity.productManagementEntity;

import com.example.e_commerce.entity.user.Store;
import com.example.e_commerce.entity.user.orders.OrderProducts;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="product",schema="ecommerce")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;


    private String description;

    @NotNull
    @Min(0)
    private double price;

    @NotNull
    @Min(0)
    private int stock;

    @NotNull
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, fetch = FetchType.EAGER)
    @JoinColumn(name="store_id")
    private Store store;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="category_id")
    private Category category;

    @Min(0)
    private double rating;

    @Min(0)
    @Column(name="sell_count")
    private int sellCount;

    @JsonBackReference
    @OneToMany(mappedBy="productId", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<OrderProducts> orderProducts;
}
