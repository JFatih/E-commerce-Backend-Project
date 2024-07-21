package com.example.e_commerce.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "card_details",schema = "ecommerce")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CardDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne( cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn(name = "app_user_id")
    private ApplicationUser applicationUser;

    @Column(name = "card_no")
    private String cardNo;

    @Column(name = "expire_month")
    private Integer expireMonth;

    @Column(name = "expire_year")
    private Integer expireYear;

    @Column(name = "name_on_card")
    private String nameOnCard;

}
