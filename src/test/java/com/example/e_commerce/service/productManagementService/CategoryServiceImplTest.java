package com.example.e_commerce.service.productManagementService;

import com.example.e_commerce.dto.ProductManagementDto.CategoryRequestDto;
import com.example.e_commerce.entity.productManagementEntity.Category;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.productManagementRepository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class CategoryServiceImplTest {

    private CategoryService categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    @BeforeEach
    void setUp() {
        categoryService = new CategoryServiceImpl(categoryRepository);
    }

    @Test
    void findAllCategory() {
        categoryService.findAll();
        verify(categoryRepository).findAll();
    }

    @Test
    void findCategoryByCode() {
        Category mockCategory = new Category();
        mockCategory.setCode("k:tisort");
        given(categoryRepository.findByCode("k:tisort")).willReturn(Optional.of(mockCategory));

        Category result = categoryService.findByCode("k:tisort");
        assertThat(result).isNotNull();
        assertThat(result.getCode()).isEqualTo("k:tisort");
        verify(categoryRepository).findByCode("k:tisort");
    }

    @Test
    void findCategoryById() {
        Category mockCategory = new Category();
        mockCategory.setId(1L);
        given(categoryRepository.findById(1L)).willReturn(Optional.of(mockCategory));

        Category result = categoryService.findById(1L);
        verify(categoryRepository).findById(1L);
    }

    @Test
    void saveAllCategories() {
        List<CategoryRequestDto> newCategories = new ArrayList<>();
        CategoryRequestDto newCategory = new CategoryRequestDto();
        newCategory.setCode("e:parfüme");
        newCategory.setTitle("malzeme");
        newCategory.setImg("xyz");
        newCategory.setRating(3.5);
        newCategory.setGender("k");
        newCategories.add(newCategory);
        categoryService.saveAll(newCategories);

        List<Category> newCategories1 = new ArrayList<>();
        Category newCategory1 = new Category();
        newCategory1.setCode("e:parfüme");
        newCategory1.setTitle("malzeme");
        newCategory1.setImg("xyz");
        newCategory1.setRating(3.5);
        newCategory1.setGender("k");
        newCategories1.add(newCategory1);
        verify(categoryRepository).saveAll(newCategories1);
    }

    @Test
    void saveCategory() {
        CategoryRequestDto newCategory = new CategoryRequestDto();
        newCategory.setCode("e:parfüme");
        newCategory.setTitle("malzeme");
        newCategory.setImg("xyz");
        newCategory.setRating(3.5);
        newCategory.setGender("k");
        categoryService.save(newCategory);

        Category newCategory1 = new Category();
        newCategory1.setCode("e:parfüme");
        newCategory1.setTitle("malzeme");
        newCategory1.setImg("xyz");
        newCategory1.setRating(3.5);
        newCategory1.setGender("k");
        verify(categoryRepository).save(newCategory1);
    }

    @Test
    void existCategoryIsSave() {
        CategoryRequestDto newCategory = new CategoryRequestDto();
        newCategory.setCode("e:parfüme");
        newCategory.setTitle("malzeme");
        newCategory.setImg("xyz");
        newCategory.setRating(3.5);
        newCategory.setGender("k");

        Category newCategory1 = new Category();
        newCategory1.setCode("e:parfüme");
        newCategory1.setTitle("malzeme");
        newCategory1.setImg("xyz");
        newCategory1.setRating(3.5);
        newCategory1.setGender("k");

        //stubbing
        given(categoryRepository.findByCode("e:parfüme")).willReturn(Optional.of(newCategory1));
        assertThatThrownBy( () -> categoryService.save(newCategory))
                .isInstanceOf(ApiException.class)
                        .hasMessageContaining("e:parfüme is existing");

        verify(categoryRepository, never()).save(newCategory1);
    }
}