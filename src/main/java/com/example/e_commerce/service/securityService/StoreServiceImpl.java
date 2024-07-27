package com.example.e_commerce.service.securityService;

import com.example.e_commerce.entity.user.Store;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.securityRepository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store findById(long id) {
        return storeRepository.findById(id).orElseThrow(()-> new ApiException("Store data not found", HttpStatus.INTERNAL_SERVER_ERROR));
    }

    @Override
    public Optional<Store> findByBankAccount(String bankAccount) {
        return storeRepository.findByBankAccount(bankAccount);
    }

    @Override
    public Optional<Store> findByTaxNo(String taxNo) {
        return storeRepository.findByTaxNo(taxNo);
    }

    @Override
    public Optional<Store> findByStoreName(String storeName) {
        return storeRepository.findByStoreName(storeName);
    }

    @Override
    public Optional<Store> findByPhone(String phone) {
        return storeRepository.findByPhone(phone);
    }


}
