package com.example.e_commerce.service.productManagementService;

import com.example.e_commerce.dto.ProductManagementDto.ImageRequestDto;
import com.example.e_commerce.dto.ProductManagementDto.ProductRequestDto;
import com.example.e_commerce.entity.productManagementEntity.Category;
import com.example.e_commerce.entity.productManagementEntity.Image;
import com.example.e_commerce.entity.productManagementEntity.Product;
import com.example.e_commerce.entity.user.Store;
import com.example.e_commerce.repository.productManagementRepository.ProductRepository;
import com.example.e_commerce.service.securityService.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private UserService userService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl(productRepository);
    }

    @Test
    void save() {
        Product pr = new Product();
        pr.setId(1L);
        pr.setName("oyuncak");
        pr.setDescription("abc");
        pr.setPrice(10);
        pr.setStock(5);
        pr.setStore(new Store(1L,"abc","05323223232","T1234V111111","TR1111444455556666"));
        pr.setCategory(new Category(1L,"e:kıy","kıy","aaa",3.0,"e"));
        pr.setRating(3.5);
        pr.setSellCount(10);

        List<Image> images = new ArrayList<>();
        Image img = new Image(1L,"xyz",0L,pr);
        images.add(img);
        pr.setImages(images);

        ProductRequestDto req = new ProductRequestDto();
        req.setName("oyuncak");
        req.setDescription("abc");
        req.setPrice(10);
        req.setStock(5);
        req.setStore_id(1L);
        req.setCategory_id(1L);
        req.setRating(3.5);
        req.setSell_count(10);

        List<ImageRequestDto> images1 = new ArrayList<>();
        ImageRequestDto img1 = new ImageRequestDto("xyz");
        images1.add(img1);
        req.setImages(images1);

//        productService.save(req,"test@gmail.com");
//        when(userService.findByEmail("test@gmail.com")).thenReturn(user);
//        verify(productRepository).save(pr);

    }

    @Test
    void findByParameter() {
        Product pr = new Product();
        pr.setId(1L);
        pr.setName("oyuncak");
        pr.setDescription("abc");
        pr.setPrice(10);
        pr.setStock(5);
        pr.setStore(new Store(1L,"abc","05323223232","T1234V111111","TR1111444455556666"));
        pr.setCategory(new Category(1L,"e:kıy","kıy","aaa",3.0,"e"));
        pr.setRating(3.5);
        pr.setSellCount(10);

        List<Image> images = new ArrayList<>();
        Image img = new Image(1L,"xyz",0L,pr);
        images.add(img);
        pr.setImages(images);


    }

    @Test
    void findById() {
        Product mockProduct = new Product();
        mockProduct.setId(1L);

        //stubbing
        given(productRepository.findById(1L)).willReturn(Optional.of(mockProduct));

        Product foundProduct = productService.findById(1L);

        verify(productRepository).findById(1L);

        assertEquals(mockProduct.getId(),foundProduct.getId());
    }

    @Test
    void findAll() {
        productService.findAll();
        verify(productRepository).findAll();
    }
}