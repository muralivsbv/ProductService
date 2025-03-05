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

                return productService.addProduct(requestDto);
    }

    //updating a product it should be Patch mapping but fakestore is not supporting patch so used PUTmapping
    @PutMapping("/products{id}")
    public Product updateProduct(@PathVariable("id") Long id,
                                 @RequestBody ProductRequestDto requestDto){
        return productService.updateProduct(id,requestDto);

    }

    @DeleteMapping("/products/{id}")
    public boolean deleteProduct(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }


}


// for sampleadding a product through postman :
/* sample data:
select POST in postnam and hit api http://localhost:8082/products/
with below body

{
    "title" : "banaras saree",
    "price" : 3050,
    "description":"Your perfect wedding fancy look",
    "category":"wedding wear",
    "image" : "https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500"

}
 */