package com.example.e_commerce.repository.productManagementRepository;

import com.example.e_commerce.entity.productManagementEntity.Category;
import com.example.e_commerce.entity.productManagementEntity.Image;
import com.example.e_commerce.entity.productManagementEntity.Product;
import com.example.e_commerce.entity.user.RoleCode;
import com.example.e_commerce.entity.user.Store;
import com.example.e_commerce.repository.securityRepository.StoreRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {

        Product newProduct = new Product();
        newProduct.setName("xyz");
        newProduct.setDescription("abc");
        newProduct.setPrice(15);
        newProduct.setStock(10);

        Store newStore = new Store();
        newStore.setName("ali");
        newStore.setPhone("05324525252");
        newStore.setTaxNo("T1234V123451");
        newStore.setBankAccount("1234123412341234");
        newProduct.setStore(newStore);

        Category newCategory = new Category();
        newCategory.setCode("e:parfüme");
        newCategory.setTitle("malzeme");
        newCategory.setImg("xyz");
        newCategory.setRating(3.5);
        newCategory.setGender("k");
        newProduct.setCategory(newCategory);

        categoryRepository.save(newCategory);

        newProduct.setRating(3.5);
        newProduct.setSellCount(25);


        Optional<Product> foundProduct = productRepository.findByProductName(newProduct.getName());
        if(foundProduct.isEmpty()){
            productRepository.save(newProduct);
        }
    }

    @AfterEach
    void tearDown() {
        Product foundProduct = productRepository.findByProductName("xyz").get();
        productRepository.delete(foundProduct);
        Store foundStore = storeRepository.findByTaxNo("T1234V123451").get();
        storeRepository.delete(foundStore);
        Category foundCategory = categoryRepository.findByCode("e:parfüme").get();
        categoryRepository.delete(foundCategory);
    }

    @Test
    void findByProductName() {
        Product foundProduct = productRepository.findByProductName("xyz").get();
        assertNotNull(foundProduct);
        assertEquals(15,foundProduct.getPrice());
        assertEquals("xyz",foundProduct.getName());
    }

    @Test
    void findByProductPrice() {
        Product foundProduct = productRepository.findByProductPrice(15).get();
        assertNotNull(foundProduct);
        assertEquals(15,foundProduct.getPrice());
        assertEquals("xyz",foundProduct.getName());
    }
}