package com.product_backend.controller;

import java.util.List;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.product_backend.dto.ProductDTO;
import com.product_backend.service.ProductService;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@Validated
@RestController
@RequestMapping("/api/v1")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> createProduct(@RequestBody ProductDTO productDTO){
        Objects.requireNonNull(productDTO, "Product cannot be null");
        logger.info("Start adding product : {}", productDTO);
        ProductDTO product = productService.createdProduct(productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String productId){
        logger.info("Start deleting product with id : {}", productId);
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productId+" deleted");
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getProduct(@PathVariable("id") String productId){
        logger.info("Start fetching the product with id : {}", productId);
        ProductDTO productDTO = productService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String productId, 
                                                @RequestBody ProductDTO productDTO){
        Objects.requireNonNull(productDTO, "Product cannot be null");
        logger.info("Start updating product with id : {} and new product information {}", 
        productId, productDTO);
        ProductDTO updatedProductDto = productService.updateProduct(productId, productDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updatedProductDto);
    }

    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> getAllProducts(
        @RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        logger.info("Start fetching all product");
        List<ProductDTO> productDTOList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK).body(productDTOList);
    }

    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> deleteMultipleProduct(@RequestBody List<String> productIds){
        logger.info("Start deleting all products with id : {}", productIds);
        productService.deleteMultipleProduct(productIds);
        return ResponseEntity.status(HttpStatus.OK).body("Deleted multiple product ids");
    }
    
}