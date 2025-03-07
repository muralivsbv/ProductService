package com.productservice.products.service;

import com.productservice.products.dtos.ProductRequestDto;
import com.productservice.products.exceptions.ProductNotFoundException;
import com.productservice.products.models.Product;
import com.productservice.products.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@Service("DBProductService") Better to use this one as we are sure of bean name to put in qualifer of the product controller
public class DBProductService implements IProductService{

    @Autowired
    ProductRepository productRepository;
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        System.out.println("coming into DB getSingleProduct method : "+id);

        if(id>400){
            throw new ArithmeticException();
        }
        if(id>200 && id<=400){

            throw new ProductNotFoundException();
        }
        return productRepository.findByid(id);
    }


    public Product getSingleProductbyName(String name){
        System.out.println("coming into DB getSingleProduct method : "+name);

        return productRepository.findByName(name);
    }

    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product addProduct(ProductRequestDto requestDto) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto requestDto) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
    }
}
