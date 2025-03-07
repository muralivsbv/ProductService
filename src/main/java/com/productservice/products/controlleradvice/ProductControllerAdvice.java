package com.productservice.products.controlleradvice;

import com.productservice.products.dtos.ProductResponseSelf;
import com.productservice.products.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProductControllerAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductResponseSelf> handlingInvalidProduct(){
        System.out.println("Entered into separate pnf exception from advice : " );
        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Product does not exist from advice");
        return new ResponseEntity<>(productResponseSelf, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity<ProductResponseSelf> handlingArithmeticException(){
        System.out.println("Entered into separate Arithmetic exception from advice : " );
        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "something went wrong from advice");
        return new ResponseEntity<>(productResponseSelf, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
