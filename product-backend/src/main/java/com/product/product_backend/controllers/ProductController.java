package com.product.product_backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.product.product_backend.dto.ProductDTO;
import com.product.product_backend.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> createProduct(@RequestBody ProductDTO productDto){
        ProductDTO product = productService.createdProduct(productDto);
        return ResponseEntity.ok(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable("id") String productId){
        productService.deleteProduct(productId);
        return ResponseEntity.ok(productId+" deleted");
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") String productId){
        ProductDTO productDTO = productService.getProduct(productId);
        return ResponseEntity.ok(productDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String productIdString, 
                                                ProductDTO productDTO){
        ProductDTO updatedProductDto = productService.updateProduct(productIdString, productDTO);
        return ResponseEntity.ok(updatedProductDto);
    }

    @GetMapping
    public ResponseEntity<Object> getAllProducts(){
        List<ProductDTO> productDTOList = productService.getAllProducts();
        return ResponseEntity.ok(productDTOList);
    }

    public ResponseEntity<Object> deleteMultipleProduct(@RequestBody List<String> productIds){
        productService.deleteMultipleProduct(productIds);
        return ResponseEntity.ok("Deleted multiple product ids")
    }
    
}