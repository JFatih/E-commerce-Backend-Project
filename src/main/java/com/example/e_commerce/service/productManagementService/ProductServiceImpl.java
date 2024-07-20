package com.example.e_commerce.service.productManagementService;

import com.example.e_commerce.dto.ProductManagementDto.ImageRequestDto;
import com.example.e_commerce.dto.ProductManagementDto.ProductRequestDto;
import com.example.e_commerce.dto.ProductManagementDto.ProductResponseWithCountDto;
import com.example.e_commerce.entity.productManagementEntity.Category;
import com.example.e_commerce.entity.productManagementEntity.Image;
import com.example.e_commerce.entity.productManagementEntity.Product;
import com.example.e_commerce.entity.user.ApplicationUser;
import com.example.e_commerce.exceptions.ApiException;
import com.example.e_commerce.mapper.ProductMapper;
import com.example.e_commerce.repository.productManagementRepository.ProductRepository;
import com.example.e_commerce.service.securityService.UserService;
import com.example.e_commerce.validation.Validation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EntityManager entityManager;

    @Transactional
    @Override
    public Product save(ProductRequestDto p, String username) {

        String productName = p.getName().replaceAll("\\s", "");
        double productPrice = p.getPrice();

        if(productRepository.findByProductName(productName).isPresent() &&
                productRepository.findByProductPrice(productPrice).isPresent() ){

            throw new ApiException("Product name: " + productName + " exist So cant added.", HttpStatus.BAD_REQUEST);
        }


        ApplicationUser user =  userService.findByEmail(username).orElseThrow( () -> new ApiException("Currently user data not exist!", HttpStatus.INTERNAL_SERVER_ERROR));

        Product newProduct = new Product();

        newProduct.setName(p.getName());
        newProduct.setDescription(p.getDescription());
        newProduct.setPrice(p.getPrice());
        newProduct.setStock(p.getStock());
        newProduct.setStore(user.getStore());
        Category c = categoryService.findDataById(p.getCategory_id());
        System.out.println(c);
        newProduct.setCategory(c);

        newProduct.setRating(p.getRating());
        newProduct.setSellCount(p.getSell_count());

        List<Image> images = new ArrayList<>();
        long index = 0L;
        for(ImageRequestDto img : p.getImages()){

            Image newImg = new Image();
            newImg.setUrl(img.getUrl());
            newImg.setIndex(index++);
            newImg.setProductId(newProduct);
            images.add(newImg);
        }
        newProduct.setImages(images);


        return productRepository.save(newProduct);
    }

    @Transactional
    @Override
    public List<Product> save(List<ProductRequestDto> products, String username) {

        List<Product> savedProducts = new ArrayList<>();

        for(ProductRequestDto pr: products){
            Product savedProduct = save(pr,username);
            savedProducts.add(savedProduct);
        }


        return savedProducts;
    }

    @Override
    public ProductResponseWithCountDto findByParameter(Long id, String word, String sort, Integer limit, Integer offset){

        if(id != null){
            categoryService.findDataById(id);
        }

        String baseQuery = "SELECT p FROM Product p WHERE 1=1";
        StringBuilder mainQuery = new StringBuilder(baseQuery);

        if(id != null){
            mainQuery.append(" AND p.category.id = :id");
        }

        if(word != null){
            mainQuery.append(" AND LOWER(p.description) LIKE LOWER(CONCAT('%', :word, '%'))");
        }

        if(sort != null){

            String[] sortData = sort.split(":");
            mainQuery.append(" ORDER BY p.").append(sortData[0]).append(" ").append(sortData[1]);

        }

        String finalQuery = mainQuery.toString();

        System.out.println(finalQuery);

        TypedQuery<Product> typedQuery = entityManager.createQuery(finalQuery,Product.class);

        if(id != null){
            typedQuery.setParameter("id",id);
        }

        if(word != null){
            typedQuery.setParameter("word",word);
        }

        Validation.limitValidation(limit);
        Validation.offSetValidation(limit);

        List<Product> withoutLimitList = typedQuery.getResultList();

        if(limit != null && offset != null){
            typedQuery.setFirstResult(offset);
            typedQuery.setMaxResults(limit);
        }

        List<Product> withLimitList = typedQuery.getResultList();


        return ProductMapper.ProductToProductResponse(withoutLimitList,withLimitList);
    }
}











