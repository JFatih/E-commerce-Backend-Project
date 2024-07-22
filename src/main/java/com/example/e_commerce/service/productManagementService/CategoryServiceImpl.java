package com.example.e_commerce.service.productManagementService;

import com.example.e_commerce.dto.ProductManagementDto.CategoryRequestDto;
import com.example.e_commerce.entity.productManagementEntity.Category;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.repository.productManagementRepository.CategoryRepository;
import com.example.e_commerce.validation.Validation;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;


    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByCode(String code) {
        return categoryRepository.findByCode(code)
                .orElseThrow(() -> new ApiException(code + " not existing", HttpStatus.BAD_REQUEST));
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException("Category id not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public void save(List<CategoryRequestDto> categories) {

        if(categories.isEmpty()){
            Validation.isNotExist();
        }

        for(CategoryRequestDto c : categories){
            Category newCategory = new Category();
            newCategory.setCode(c.getCode());
            newCategory.setTitle(c.getTitle());
            newCategory.setImg(c.getImg());
            newCategory.setRating(c.getRating());
            newCategory.setGender(c.getGender());

            if(findByCode(newCategory.getCode()) != null){
                Validation.categoryExist(newCategory.getCode());
            }
            categoryRepository.save(newCategory);
        }
    }


}
