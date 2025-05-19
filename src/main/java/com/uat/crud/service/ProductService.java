package com.uat.crud.service;

import com.uat.crud.model.ProductDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getAllProducts();
    Optional<ProductDTO> getProductById(Long id);
    ProductDTO saveProduct(ProductDTO productDTO);
    ProductDTO updateProduct(Long id, ProductDTO productDTO);
    Page<ProductDTO> searchProducts(String name, double minPrice, double maxPrice, int page, int size);
    void deleteProduct(Long id);
}
