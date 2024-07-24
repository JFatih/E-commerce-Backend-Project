package com.example.e_commerce.entity.user.orders;

import com.example.e_commerce.entity.productManagementEntity.Product;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_products",schema = "ecommerce")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

//    @ManyToOne
//    @JoinColumn(name = "product_id")
//    private Product product;
    private Long productId;

    private Integer count;

    private String detail;

}
