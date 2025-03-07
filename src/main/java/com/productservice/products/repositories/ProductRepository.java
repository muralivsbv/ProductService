package com.productservice.products.repositories;

import com.productservice.products.models.Product;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
   Product findByName(String name);
    Product findByid(Long id);
    Product findByNameAndid(String name,Long id)


}
