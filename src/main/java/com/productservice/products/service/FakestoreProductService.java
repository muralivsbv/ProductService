package com.productservice.products.service;

import com.productservice.products.dtos.ProductResponseDto;
import com.productservice.products.models.Category;
import com.productservice.products.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;


@Service
public class FakestoreProductService implements IProductService{

    @Autowired
    RestTemplate restTemplate;
    @Override
    public Product getSingleProduct(Long id) {
      //  RestTemplate restTemplate = new RestTemplate();
        //http://localhost:8082/product/8
        System.out.println("Entered get single product method after hitting api");
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

    private Product getProductFromResponseDto(ProductResponseDto response) {
        System.out.println("Converting response to product");
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
