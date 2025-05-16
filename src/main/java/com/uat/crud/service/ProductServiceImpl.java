package com.uat.crud.service;

import com.uat.crud.model.Product;
import com.uat.crud.model.ProductDTO;
import com.uat.crud.repository.ProductRepo;
import com.uat.crud.mapper.ProductMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepo ProductRepo;

    public ProductServiceImpl(ProductRepo ProductRepo) {
        this.ProductRepo = ProductRepo;
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        return ProductRepo.findAll().stream()
                .map(ProductMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return ProductRepo.findById(id).map(ProductMapper::toDTO);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = ProductMapper.toEntity(productDTO);
        Product savedProduct = ProductRepo.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDTO) {
        Product product = ProductRepo.findById(id).orElseThrow();
        product.setName(productDTO.name());
        product.setDescription(productDTO.description());
        product.setPrice(productDTO.price());
        Product updatedProduct = ProductRepo.save(product);
        return ProductMapper.toDTO(updatedProduct);
    }

    @Override
    public void deleteProduct(Long id) {
        ProductRepo.deleteById(id);
    }
}