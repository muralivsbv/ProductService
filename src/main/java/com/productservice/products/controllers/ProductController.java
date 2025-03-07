package com.productservice.products.controllers;

import com.productservice.products.dtos.ProductRequestDto;
import com.productservice.products.dtos.ProductResponseSelf;
import com.productservice.products.exceptions.ProductNotFoundException;
import com.productservice.products.models.Category;
import com.productservice.products.models.Product;
import com.productservice.products.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


// new productservice
@RestController
public class ProductController {


    //  @Qualifier("DBProductService")   // To make sure which service instance to be injected alternatively this can be achieved through profile in run time changing profile in application.properties
    @Autowired
    @Qualifier("fakestoreProductService")
    IProductService productService;



// below commented code is without handling any exceptions and also no wrapper(ResponseEntity) around product object// so no meaningfull message can be communicated to UI when this api invoked from front end
//    @GetMapping("/product/{id}")
//    public Product getSingleProduct(@PathVariable("id") Long id){
//
//        return productService.getSingleProduct(id);
//    }

    //handling the exceptions like null product and all using response entity
    @GetMapping("/product/{id}")
    public ResponseEntity<ProductResponseSelf> getSingleProduct(@PathVariable("id") Long id) {

        Product product;

        try{
            System.out.println("Entered into try block");
            product = productService.getSingleProduct(id);
        }catch(ProductNotFoundException e) {
            System.out.println("Entered into pnf exception : " + id);
            ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Product does not exist");
            return new ResponseEntity<>(productResponseSelf, HttpStatus.NOT_FOUND);
        } catch (ArithmeticException e) {
            System.out.println("Entered into Arithmetic exception : " +id);
            ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "something went wrong");
            return new ResponseEntity<>(productResponseSelf, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch(Exception e){
            System.out.println("Entered general exception");
            throw new RuntimeException();
        }

        ProductResponseSelf productResponseSelf = new ProductResponseSelf(product, "search success");
        return new ResponseEntity<>(productResponseSelf,HttpStatus.OK);

    }

    //Using exception Handler to avoid all try catch thing to make it separate from main controller code
    // to make look code neater and also if we want to override the ProductControllerAdvice exceptionhandler
    // we can override here in the code as shown example of overriding product not found - commented
    @GetMapping("/product/exception/{id}")
    public ResponseEntity<ProductResponseSelf> getSingleProductException(@PathVariable("id") Long id)
            throws ProductNotFoundException {

                    Product product = productService.getSingleProduct(id);


        ProductResponseSelf productResponseSelf = new ProductResponseSelf(product, "search success");
        return new ResponseEntity<>(productResponseSelf,HttpStatus.OK);

    }


//   Moved these to ProductControllerAdvice so that all other controllers can also us this
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ProductResponseSelf> handlingInvalidProduct(){
        System.out.println("Entered into separate pnf exception : " );
        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "Product does not exist");
        return new ResponseEntity<>(productResponseSelf, HttpStatus.NOT_FOUND);
    }

//    @ExceptionHandler(ArithmeticException.class)
//    public ResponseEntity<ProductResponseSelf> handlingArithmeticException(){
//        System.out.println("Entered into separate Arithmetic exception : " );
//        ProductResponseSelf productResponseSelf = new ProductResponseSelf(null, "something went wrong");
//        return new ResponseEntity<>(productResponseSelf, HttpStatus.INTERNAL_SERVER_ERROR);
//    }

    //Getmapping with name
    @GetMapping("/product/search")
    public ResponseEntity<ProductResponseSelf> getSingleProduct(@RequestParam("name") String name) {

        Product product;


            product = productService.getSingleProductbyName(name);


        ProductResponseSelf productResponseSelf = new ProductResponseSelf(product, "search success");
        return new ResponseEntity<>(productResponseSelf,HttpStatus.OK);

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