package com.product_backend.mapper;

import org.mapstruct.Mapper;

import com.product_backend.dto.ProductDTO;
import com.product_backend.entity.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO productDto);
}
