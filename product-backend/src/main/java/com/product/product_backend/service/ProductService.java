package com.product.product_backend.service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product.product_backend.dto.ProductDTO;
import com.product.product_backend.entity.Product;
import com.product.product_backend.mapper.ProductMapper;
import com.product.product_backend.repository.ProductRepository;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductDTO createdProduct(ProductDTO productDto){
        Product product = productMapper.toEntity(productDto);
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public void deleteProduct(String productId){
        productRepository.deleteById(productId); 
    }

    public ProductDTO getProduct(String productId){
        Product product = productRepository.getById(productId);
        ProductDTO productDTO = productMapper.toDto(product);
        return productDTO;
    }

    public ProductDTO updateProduct(String productId, ProductDTO productDTO){
        Product product = productMapper.toEntity(productDTO);
        product.setProductId(productId);
        Product updatedProduct = productRepository.save(product);
        ProductDTO updatedProductDTO = productMapper.toDto(updatedProduct);
        return updatedProductDTO;
    }

    public List<ProductDTO> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = productList.stream()
                                                        .map(p->productMapper.toDto(p))
                                                        .collect(Collectors.toList());
        return productDTOList;
    }

    public void deleteMultipleProduct(List<String> productIds){
        productIds.stream()
                    .forEach(pId->productRepository.deleteById(pId));
    }

}
