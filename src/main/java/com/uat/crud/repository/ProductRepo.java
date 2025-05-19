package com.uat.crud.repository;

import com.uat.crud.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Long> {
    Page<Product> findByNameContainingIgnoreCaseAndPriceBetween(
            String name, double minPrice, double maxPrice, Pageable pageable
    );
}
