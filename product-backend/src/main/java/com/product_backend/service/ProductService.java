package com.product_backend.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.product_backend.dto.ProductDTO;
import com.product_backend.entity.Product;
import com.product_backend.exception.custom.ProductException;
import com.product_backend.mapper.ProductMapper;
import com.product_backend.repository.ProductRepository;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    public ProductDTO createdProduct(ProductDTO productDTO){
        logger.info("Saving product: {} in database", productDTO);
        try{
            Product product = productMapper.toEntity(productDTO);
            String productId = UUID.randomUUID().toString();
            product.setProductId(productId);
            Product savedProduct = productRepository.save(product);
            return productMapper.toDto(savedProduct);
        }catch(Exception ex){
            String message = String.format("Failed to save product {}", productDTO);
            logger.error(message);
            throw new ProductException(message);
        }
        
    }

    public void deleteProduct(String productId){
        try{
            logger.info("Deleting product with id: {}", productId);
            productRepository.deleteById(productId); 
        }catch(Exception ex){
            String message = String.format("Failed to delete product id {}", productId);
            logger.error(message);
            throw new ProductException(message);
        }
        
    }

    public ProductDTO getProduct(String productId){
        logger.info("Fetching product from DB with id: {}", productId);
        Optional<Product> product = productRepository.findById(productId);
        if(!product.isPresent()){
            String message = String.format("Product with id : %s does not exists.", productId);
            logger.error(message);
            throw new ProductException(message);
        }
        ProductDTO productDTO = productMapper.toDto(product.get());
        return productDTO;
    }

    public ProductDTO updateProduct(String productId, ProductDTO productDTO){
        try{
            logger.info("Updating product: {} in DB", productDTO);
            Product product = productMapper.toEntity(productDTO);
            product.setProductId(productId);
            Product updatedProduct = productRepository.save(product);
            ProductDTO updatedProductDTO = productMapper.toDto(updatedProduct);
            return updatedProductDTO;
        }catch(Exception ex){
            String message = String.format("Error occured while updating product id {}", productId);
            logger.error(message);
            throw new ProductException(message);
        }
        
        
    }

    public List<ProductDTO> getAllProducts(){
        try{
            logger.info("Fetching all products from DB");
            List<Product> productList = productRepository.findAll();
            List<ProductDTO> productDTOList = productList.stream().map(p->productMapper.toDto(p))
                                                .collect(Collectors.toList());
            return productDTOList;
        }catch(Exception ex){
            String message = "Error occured while fetching all product details";
            logger.error(message);
            throw new ProductException(message);
        }
        
    }

    public void deleteMultipleProduct(List<String> productIds){
        logger.info("Deleting all products from DB with Ids: {}", productIds);
        productIds.stream()
                    .forEach(pId->{
                        try{
                            productRepository.deleteById(pId);
                        }catch(Exception ex){
                            logger.error("Product id {} can't be deleted", pId);
                        }
                    });    
    }

}
