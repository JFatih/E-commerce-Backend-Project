package com.example.e_commerce.entity.user;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="store",schema="public")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Store {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String phone;

    @Column(name="tax_no")
    private String taxNo;

    @Column(name="bank_account")
    private String bankAccount;

}
