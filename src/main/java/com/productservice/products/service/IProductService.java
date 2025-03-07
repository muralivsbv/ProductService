package com.productservice.products.service;

import com.productservice.products.dtos.ProductRequestDto;
import com.productservice.products.exceptions.ProductNotFoundException;
import com.productservice.products.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IProductService {
    Product getSingleProduct(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();

    Product addProduct(ProductRequestDto requestDto);

    Product updateProduct(Long id, ProductRequestDto requestDto);

    boolean deleteProduct(Long id);

    Product getSingleProductbyName(String name);
}
