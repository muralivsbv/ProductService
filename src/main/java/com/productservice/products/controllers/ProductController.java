package com.productservice.products.controllers;

import com.productservice.products.dtos.ProductRequestDto;
import com.productservice.products.models.Category;
import com.productservice.products.models.Product;
import com.productservice.products.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// new productservice
@RestController
public class ProductController {
    @Autowired
     IProductService productService;



    @GetMapping("/product/{id}")
    public Product getSingleProduct(@PathVariable("id") Long id){

        return productService.getSingleProduct(id);
    }

    @GetMapping("/products")
    public List<Product> getALlProducts(){

        return productService.getAllProducts();
    }

    @GetMapping("products/categories")
    public List<Category> getAllCategories(){
        return new ArrayList<>();
    }

    @GetMapping("products/categories/{id}")
    public List<Category> getAllCategories(@PathVariable("id") Long id){
        return new ArrayList<>();
    }

    // adding new product
    @PostMapping("/products")
    public Product addProduct(@RequestBody ProductRequestDto requestDto){
                 return new Product();
    }

    //updating a product
    @PatchMapping("/products{id}")
    public Product updateProduct(@PathVariable("id") Long id,
                                 @RequestBody ProductRequestDto requestDto){
        return new Product();
    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){
        return true;
    }


}
