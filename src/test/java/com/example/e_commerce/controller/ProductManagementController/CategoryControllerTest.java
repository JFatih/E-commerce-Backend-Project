package com.example.e_commerce.controller.ProductManagementController;

import com.example.e_commerce.config.SecurityConfig;
import com.example.e_commerce.dto.ProductManagementDto.CategoryRequestDto;
import com.example.e_commerce.entity.productManagementEntity.Category;
import com.example.e_commerce.entity.user.RoleCode;
import com.example.e_commerce.service.productManagementService.CategoryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//Integration Test => Entegrasyon Test


@WebMvcTest(CategoryController.class)
@ExtendWith(MockitoExtension.class)
class CategoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CategoryService categoryService;

    //Controller test hatası burada//
    @Test
    void findAll() throws Exception {
        Category category1 = new Category();
        category1.setCode("e:parfüme");
        category1.setTitle("malzeme");
        category1.setImg("xyz");
        category1.setRating(3.5);
        category1.setGender("k");

        Category category2 = new Category();
        category2.setCode("e:parfüme");
        category2.setTitle("malzeme");
        category2.setImg("xyz");
        category2.setRating(3.5);
        category2.setGender("k");

        given(categoryService.findAll()).willReturn(Arrays.asList(category1, category2));

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/categories")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].code").value("e:parfüme"))
                .andExpect(jsonPath("$[1].code").value("e:parfüme"));
    }

    @Test
//    @WithMockUser(username = "test@gmail.com", password = "{noop}Test@123", roles = {"admin","store","customer"})
    void saveCategory() throws Exception {
        CategoryRequestDto newCategoryRequestDto = new CategoryRequestDto();
        newCategoryRequestDto.setCode("e:parfüme");
        newCategoryRequestDto.setTitle("malzeme");
        newCategoryRequestDto.setImg("xyz");
        newCategoryRequestDto.setRating(3.5);
        newCategoryRequestDto.setGender("k");

        Category savedCategory = new Category();
        savedCategory.setCode("e:parfüme");
        savedCategory.setTitle("malzeme");
        savedCategory.setImg("xyz");
        savedCategory.setRating(3.5);
        savedCategory.setGender("k");

        //Stubbing
        mockMvc.perform(post("/categories/add")
                        .with(user("test@gmail.com").password("{noop}Test@123").roles("admin","store","customer"))
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(newCategoryRequestDto))
                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isCreated())
                        .andExpect(jsonPath("$.message").value("Category saved!"))
                        .andExpect(jsonPath("$.category.code").value("e:parfüme"))
                        .andExpect(jsonPath("$.category.title").value("malzeme"));

        verify(categoryService).save(newCategoryRequestDto);

    }


    void testSaveCategories() {
    }

    public static String jsonToString(Object object){
        try{
            return new ObjectMapper().writeValueAsString(object);
        }catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }
}