package com.uat.crud.mapper;

import com.uat.crud.model.Product;
import com.uat.crud.model.ProductDTO;

public class ProductMapper {

    public static ProductDTO toDTO(Product product) {
        if (product == null) return null;
        return new ProductDTO(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice()
        );
    }

    public static Product toEntity(ProductDTO dto) {
        if (dto == null) return null;
        Product product = new Product();
        product.setId(dto.id());
        product.setName(dto.name());
        product.setDescription(dto.description());
        product.setPrice(dto.price());
        return product;
    }
}