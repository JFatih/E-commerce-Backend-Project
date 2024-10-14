package com.example.e_commerce.repository.securityRepository;

import com.example.e_commerce.entity.user.Store;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreRepositoryTest {

    @Autowired
    private StoreRepository storeRepository;

    private Store testStore;

    @BeforeEach
    void setUp() {
        testStore = new Store();
        testStore.setName("Test Store");
        testStore.setPhone("05978527496");
        testStore.setTaxNo("T1231V123123");
        testStore.setBankAccount("TR1111222233334444");
        storeRepository.save(testStore);
    }

    @AfterEach
    void tearDown() {
        storeRepository.delete(testStore);
    }

    @Test
    void findByTaxNo() {
        Optional<Store> foundStore = storeRepository.findByTaxNo("T1231V123123");

        assertTrue(foundStore.isPresent());
        assertEquals("Test Store",foundStore.get().getName());
        assertEquals("05978527496",foundStore.get().getPhone());
        assertEquals("T1231V123123",foundStore.get().getTaxNo());
        assertEquals("TR1111222233334444",foundStore.get().getBankAccount());
    }

    @Test
    void findByBankAccount() {
        Optional<Store> foundStore = storeRepository.findByBankAccount("TR1111222233334444");

        assertTrue(foundStore.isPresent());
        assertEquals("Test Store",foundStore.get().getName());
        assertEquals("05978527496",foundStore.get().getPhone());
        assertEquals("T1231V123123",foundStore.get().getTaxNo());
        assertEquals("TR1111222233334444",foundStore.get().getBankAccount());
    }

    @Test
    void findByStoreName() {
        Optional<Store> foundStore = storeRepository.findByStoreName("Test Store");

        assertTrue(foundStore.isPresent());
        assertEquals("Test Store",foundStore.get().getName());
        assertEquals("05978527496",foundStore.get().getPhone());
        assertEquals("T1231V123123",foundStore.get().getTaxNo());
        assertEquals("TR1111222233334444",foundStore.get().getBankAccount());
    }

    @Test
    void findByPhone() {
        Optional<Store> foundStore = storeRepository.findByPhone("05978527496");

        assertTrue(foundStore.isPresent());
        assertEquals("Test Store",foundStore.get().getName());
        assertEquals("05978527496",foundStore.get().getPhone());
        assertEquals("T1231V123123",foundStore.get().getTaxNo());
        assertEquals("TR1111222233334444",foundStore.get().getBankAccount());
    }
}