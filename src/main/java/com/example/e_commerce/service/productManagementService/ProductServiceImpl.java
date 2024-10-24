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

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EntityManager entityManager;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    @Override
    public Product save(ProductRequestDto p, String username) {

        String productName = p.getName();
        double productPrice = p.getPrice();

        if(productRepository.findByProductName(productName).isPresent()){
            throw new ApiException("Product name: " + productName + " exist So cant added.", HttpStatus.BAD_REQUEST);
        }

        ApplicationUser user =  userService.findByEmail(username);

        Product newProduct = requestDtoToProduct(p,user);

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

        String baseQuery = "SELECT p FROM Product p WHERE 1=1";
        StringBuilder mainQuery = new StringBuilder(baseQuery);

        if(id != null){
            categoryService.findById(id);
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

        TypedQuery<Product> typedQuery = entityManager.createQuery(finalQuery,Product.class);

        if(id != null){
            typedQuery.setParameter("id",id);
        }

        if(word != null){
            typedQuery.setParameter("word",word);
        }


        List<Product> withoutLimitList = typedQuery.getResultList();


        if(limit != null && offset != null){
            Validation.limitValidation(limit);
            Validation.offSetValidation(offset);
            typedQuery.setFirstResult(offset);
            typedQuery.setMaxResults(limit);
        }

        List<Product> withLimitList = typedQuery.getResultList();
        System.out.println(withLimitList.size());

        return ProductMapper.ProductToProductResponse(withoutLimitList,withLimitList);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElseThrow( () -> Validation.productIsNotExist(id));
    }

    @Override
    public ProductResponseWithCountDto findAll(){
        return ProductMapper.ProductToProductResponse(productRepository.findAll());
    }

    public Product requestDtoToProduct(ProductRequestDto p, ApplicationUser user){

        Product newProduct = new Product();

        newProduct.setName(p.getName());
        newProduct.setDescription(p.getDescription());
        newProduct.setPrice(p.getPrice());
        newProduct.setStock(p.getStock());
        newProduct.setStore(user.getStore());
        Category c = categoryService.findById(p.getCategory_id());
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

        return newProduct;
    }
}











