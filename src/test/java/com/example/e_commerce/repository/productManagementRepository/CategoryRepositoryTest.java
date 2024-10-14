//package com.example.e_commerce.repository.productManagementRepository;
//
//import com.example.e_commerce.entity.productManagementEntity.Category;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//class CategoryRepositoryTest {
//
//    private CategoryRepository categoryRepository;
//
//    @Autowired
//    public CategoryRepositoryTest(CategoryRepository categoryRepository) {
//        this.categoryRepository = categoryRepository;
//    }
//
//    @BeforeEach
//    void setUp() {
//        Category newCategory = new Category();
//        newCategory.setCode("e:parfüm");
//        newCategory.setTitle("malzeme");
//        newCategory.setImg("xyz");
//        newCategory.setRating(3.5);
//        newCategory.setGender("k");
//        Optional<Category> foundCategory = categoryRepository.findByCode("e:parfüm");
//        if(foundCategory.isEmpty()){
//            categoryRepository.save(newCategory);
//        }
//    }
//
//    @AfterEach
//    void tearDown() {
//        Optional<Category> foundCategory = categoryRepository.findByCode("e:parfüm");
//        categoryRepository.delete(foundCategory.get());
//    }
//
//    @DisplayName("Can find category by code ?")
//    @Test
//    void findByCode() {
//        Category foundCategory = categoryRepository.findByCode("e:parfüm").get();
//
//        assertNotNull(foundCategory);
//        assertEquals("e:parfüm", foundCategory.getCode());
//        assertEquals("malzeme", foundCategory.getTitle());
//        assertEquals("xyz", foundCategory.getImg());
//        assertEquals(3.5, foundCategory.getRating());
//        assertEquals("k", foundCategory.getGender());
//
//    }
//}