package com.example.e_commerce.entity.user.orders;

import com.example.e_commerce.entity.user.Address;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.entity.user.CardDetails;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "order",schema = "ecommerce")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "app_user_id")
    private ApplicationUser applicationUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "order_date")
    private String orderDate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "card_details_id")
    private CardDetails cardDetails;

    @Column(name = "card_ccv")
    private Integer cardCcv;

    private Integer price;

    @OneToMany(mappedBy = "order", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<OrderProducts> orderProducts;




}
