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
import java.util.stream.Collectors;


@Service
public class CategoryServiceImpl implements CategoryService{

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findByCode(String code) {
        return categoryRepository.findByCode(code)
                .orElseThrow(() -> new ApiException("Category code: " + code + " not existing", HttpStatus.BAD_REQUEST));
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ApiException("Category id not found", HttpStatus.NOT_FOUND));
    }

    @Transactional
    @Override
    public List<Category> saveAll(List<CategoryRequestDto> categories) {

        if( categories == null || categories.isEmpty() ){
            throw new ApiException("Category list is empty", HttpStatus.BAD_REQUEST);
        }

        List<Category> newCategories = categories.stream().map(c -> {

            Category newCategory = dtoToCategory(c);

            if(categoryRepository.findByCode(newCategory.getCode()).isPresent()){
                Validation.categoryExist(newCategory.getCode());
            }
            return newCategory;
        }).collect(Collectors.toList());

        return categoryRepository.saveAll(newCategories);
    }

    @Transactional
    @Override
    public Category save(CategoryRequestDto requestCategory) {

        if(categoryRepository.findByCode(requestCategory.getCode()).isPresent()){
            Validation.categoryExist(requestCategory.getCode());
        }

        Category newCategory = dtoToCategory(requestCategory);

        return categoryRepository.save(newCategory);
    }

    public static Category dtoToCategory(CategoryRequestDto category){
        Category newCategory = new Category();
        newCategory.setCode(category.getCode());
        newCategory.setTitle(category.getTitle());
        newCategory.setImg(category.getImg());
        newCategory.setRating(category.getRating());
        newCategory.setGender(category.getGender());
        return newCategory;
    }


}
