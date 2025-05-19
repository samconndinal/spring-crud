package com.uat.crud.controller;

import com.uat.crud.model.ProductDTO;
import com.uat.crud.response.ApiResponse;
import com.uat.crud.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/search")
    public ApiResponse<Page<ProductDTO>> searchProducts(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "0") double minPrice,
            @RequestParam(defaultValue = "1000000") double maxPrice,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<ProductDTO> result = productService.searchProducts(name, minPrice, maxPrice, page, size);
        return new ApiResponse<>(true, result);
    }

    @GetMapping
    public ApiResponse<List<ProductDTO>> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return new ApiResponse<>(true, products);
    }

    @GetMapping("/{id}")
    public ApiResponse<ProductDTO> getProductById(@PathVariable Long id) {
        Optional<ProductDTO> product = productService.getProductById(id);
        return product
                .map(p -> new ApiResponse<>(true, p))
                .orElseGet(() -> new ApiResponse<>(false, null));
    }

    @PostMapping
    public ApiResponse<ProductDTO> createProduct(@RequestBody ProductDTO productDTO) {
        ProductDTO created = productService.saveProduct(productDTO);
        return new ApiResponse<>(true, created);
    }

    @PutMapping("/{id}")
    public ApiResponse<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
        ProductDTO updated = productService.updateProduct(id, productDTO);
        return new ApiResponse<>(true, updated);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ApiResponse<>(true, null);
    }
}