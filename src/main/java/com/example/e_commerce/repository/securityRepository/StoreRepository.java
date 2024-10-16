package com.example.e_commerce.repository.securityRepository;

import com.example.e_commerce.entity.user.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {

    @Query("SELECT s FROM Store s WHERE s.taxNo = :taxNo")
    Optional<Store> findByTaxNo(String taxNo);

    @Query("SELECT s FROM Store s WHERE s.bankAccount = :bankAccount")
    Optional<Store> findByBankAccount(String bankAccount);

    @Query("SELECT s FROM Store s WHERE s.name = :storeName")
    Optional<Store> findByStoreName(String storeName);

    @Query("SELECT s FROM Store s WHERE s.phone = :phone")
    Optional<Store> findByPhone(String phone);

}
