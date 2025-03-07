package com.productservice.products.dtos;

import com.productservice.products.models.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductResponseSelf {
    private Product product;
    private String message;

    public ProductResponseSelf(Product product, String message) {
        this.product = product;
        this.message = message;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Product getProduct() {
        return product;
    }

    public String getMessage() {
        return message;
    }
}
