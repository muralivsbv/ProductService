package com.productservice.products.repositories;

import com.productservice.products.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
   Product findByName(String name);
    Optional<Product> findById(Long id);
    Product findByIdAndName(Long id,String name);


}
