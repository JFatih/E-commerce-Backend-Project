package com.example.e_commerce.mapper;

import com.example.e_commerce.dto.ProductManagementDto.ImageResponseDto;
import com.example.e_commerce.dto.ProductManagementDto.ProductRequestDto;
import com.example.e_commerce.dto.ProductManagementDto.ProductResponseDto;
import com.example.e_commerce.dto.ProductManagementDto.ProductResponseWithCountDto;
import com.example.e_commerce.entity.productManagementEntity.Image;
import com.example.e_commerce.entity.productManagementEntity.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductMapper {

    public static ProductResponseDto ProductToProductResponse(Product pr){

        ProductResponseDto pro = new ProductResponseDto();
        pro.setId(pr.getId());
        pro.setName(pr.getName());
        pro.setDescription(pr.getDescription());
        pro.setPrice(pr.getPrice());
        pro.setStore_id(pr.getStore().getId());
        pro.setCategory_id(pr.getCategory().getId());
        pro.setRating(pr.getRating());
        pro.setSell_count(pr.getSellCount());
        List<ImageResponseDto> res = new ArrayList<>();
        for(Image img : pr.getImages()){
            ImageResponseDto ima = new ImageResponseDto();
            ima.setIndex(img.getIndex());
            ima.setUrl(img.getUrl());
            res.add(ima);
        }
        pro.setImages(res);

        return pro;
    }

    public static ProductResponseWithCountDto ProductToProductResponse(List<Product> products){

        List<ProductResponseDto> newList = new ArrayList<>();

        for(Product pr : products){
            newList.add(ProductToProductResponse(pr));
        }
        ProductResponseWithCountDto response = new ProductResponseWithCountDto();
        response.setTotal(newList.size());
        response.setProducts(newList);
        return response;
    }

    public static Product productRequestToProduct(ProductRequestDto p){

        Product newProduct = new Product();

        newProduct.setName(p.getName());
        newProduct.setDescription(p.getDescription());
        newProduct.setPrice(p.getPrice());
        newProduct.setStock(p.getStock());

        return newProduct;
    }
}
