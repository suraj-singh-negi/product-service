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
        Product product = productMapper.toEntity(productDTO);
        String productId = UUID.randomUUID().toString();
        product.setProductId(productId);
        logger.info("Saving product: {} in database", productDTO);
        Product savedProduct = productRepository.save(product);
        return productMapper.toDto(savedProduct);
    }

    public void deleteProduct(String productId){
        logger.info("Deleting product with id: {}", productId);
        productRepository.deleteById(productId); 
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
        Product product = productMapper.toEntity(productDTO);
        product.setProductId(productId);
        logger.info("Updating product: {} in DB", productDTO);
        Product updatedProduct = productRepository.save(product);
        ProductDTO updatedProductDTO = productMapper.toDto(updatedProduct);
        return updatedProductDTO;
    }

    public List<ProductDTO> getAllProducts(){
        logger.info("Fetching all products from DB");
        List<Product> productList = productRepository.findAll();
        List<ProductDTO> productDTOList = productList.stream()
                                                        .map(p->productMapper.toDto(p))
                                                        .collect(Collectors.toList());
        return productDTOList;
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
