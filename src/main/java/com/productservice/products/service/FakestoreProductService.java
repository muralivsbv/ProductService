package com.productservice.products.service;

import com.productservice.products.dtos.ProductRequestDto;
import com.productservice.products.dtos.ProductResponseDto;
import com.productservice.products.exceptions.ProductNotFoundException;
import com.productservice.products.models.Category;
import com.productservice.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakestoreProductService implements IProductService{

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Product getSingleProduct(Long id) throws ProductNotFoundException {
        // instead of creating a object of resttemplate here we can autowire by marking it as bean in config packgae
        //  .. eventhough it is code(RestTemplate) outside our project we can mark it as a bean using config
      //  RestTemplate restTemplate = new RestTemplate();
        //http://localhost:8082/product/8
        System.out.println("Entered get single product method after hitting api : " +id);


        if(id>40){
            throw new ArithmeticException();
        }
        if(id>20 && id<=40){

            throw new ProductNotFoundException();
        }
        ProductResponseDto response = restTemplate.getForObject("https://fakestoreapi.com/products/" + id,
                ProductResponseDto.class);

        return getProductFromResponseDto(response);

    }

    @Override
    public List<Product> getAllProducts() {
        ProductResponseDto[] products = restTemplate.getForObject("https://fakestoreapi.com/products",
                ProductResponseDto[].class);

        List<Product> result = new ArrayList<>();
        for(ProductResponseDto productResponseDto: products){
            Product product = getProductFromResponseDto(productResponseDto);
            result.add(product);

        }

        return result;
    }

    @Override
    public Product addProduct(ProductRequestDto requestDto) {
         System.out.println("Got in to add product method");
        String url =  "https://fakestoreapi.com/products";

        ProductResponseDto productResponseDto = restTemplate.postForObject(url, requestDto,
                                                                      ProductResponseDto.class);
        return getProductFromResponseDto(productResponseDto);
    }

    @Override
    public Product updateProduct(Long id, ProductRequestDto requestDto) {
        System.out.println("Entered updating product : "+id);
        String url = "https://fakestoreapi.com/products/"+id;
//        ProductResponseDto productResponseDto =
//             restTemplate.patchForObject(url+id, requestDto, ProductResponseDto.class);
//        return getProductFromResponseDto(productResponseDto);
        restTemplate.put(url,requestDto);
// as suggested by chatgpt also its not supoorted using https
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<ProductRequestDto> requestEntity = new HttpEntity<>(requestDto, headers);
//
//        restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Void.class);
   //     return getSingleProduct(id);

        return new Product();
    }

    @Override
    public boolean deleteProduct(Long id) {
        System.out.println("Entered deleting product : "+id);
        String url = "https://fakestoreapi.com/products/"+id;
        restTemplate.delete(url);
        System.out.println("resttemplate deletion step done");
        return true;
    }

    private Product getProductFromResponseDto(ProductResponseDto response) {
        System.out.println("Converting response to product : "+response.getId());
        Product product= new Product();
        product.setId(response.getId());
        product.setName(response.getTitle());
        product.setPrice(response.getPrice());
        product.setDescription(response.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(response.getCategory());
        product.setImageUrl(response.getImage());
        return product;
    }
}
