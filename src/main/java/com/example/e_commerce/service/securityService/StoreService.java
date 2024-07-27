package com.example.e_commerce.service.securityService;

import com.example.e_commerce.entity.user.Store;

import java.util.Optional;

public interface StoreService {

    Store findById(long id);

    Optional<Store> findByBankAccount(String bankAccount);

    Optional<Store> findByTaxNo(String taxNo);

    Optional<Store> findByStoreName(String storeName);

    Optional<Store> findByPhone(String phone);
}
