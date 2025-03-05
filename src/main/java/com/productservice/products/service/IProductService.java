package com.productservice.products.service;

import com.productservice.products.dtos.ProductRequestDto;
import com.productservice.products.models.Product;

import java.util.List;

public interface IProductService {
    Product getSingleProduct(Long id);
    List<Product> getAllProducts();

    Product addProduct(ProductRequestDto requestDto);

    Product updateProduct(Long id, ProductRequestDto requestDto);

    boolean deleteProduct(Long id);
}
