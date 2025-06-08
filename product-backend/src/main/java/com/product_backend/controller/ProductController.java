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
import com.product_backend.response.ApiResponse;
import com.product_backend.service.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static com.product_backend.utility.Constants.API_V1;

@Tag(name = "Product API")
@Validated
@RestController
@RequestMapping(API_V1+"/product")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;

    @Operation(summary = "Create a new product", description = "This endpoint creates a product.")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> createProduct(@RequestBody ProductDTO productDTO){
        Objects.requireNonNull(productDTO, "Product cannot be null");
        logger.info("Start adding product : {}", productDTO);
        ProductDTO product = productService.createdProduct(productDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
        .body(new ApiResponse<ProductDTO>(true, "Product created successfully", product));
    }

    @Operation(summary = "Delete a product", description = "This endpoint deletes a product with given product id.")
    @DeleteMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deleteProduct(@PathVariable("id") String productId){
        logger.info("Start deleting product with id : {}", productId);
        productService.deleteProduct(productId);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ApiResponse<>(true, "Product deleted", null));
    }

    @Operation( summary = "Get a product", description = "This endpoint fetchs a product with given product id.")
    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> getProduct(@PathVariable("id") String productId){
        logger.info("Start fetching the product with id : {}", productId);
        ProductDTO productDTO = productService.getProduct(productId);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ApiResponse<ProductDTO>(true, "Product fetched successfully", productDTO));
    }

    @Operation( summary = "Update a product", description = "This endpoint updates a product with given product id.")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE,
    produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> updateProduct(@PathVariable("id") String productId, 
                                                @RequestBody ProductDTO productDTO){
        Objects.requireNonNull(productDTO, "Product cannot be null");
        logger.info("Start updating product with id : {} and new product information {}", 
        productId, productDTO);
        ProductDTO updatedProductDto = productService.updateProduct(productId, productDTO);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ApiResponse<ProductDTO>(true, "Product is updated successfully", updatedProductDto));
    }

    @Operation( summary = "Get all products", description = "This endpoint fetches all products." )
    @GetMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> getAllProducts(
        @RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
        @RequestParam(name = "pageSize", required = false, defaultValue = "10") int pageSize){
        logger.info("Start fetching all product");
        List<ProductDTO> productDTOList = productService.getAllProducts();
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ApiResponse<List<ProductDTO>>(true, "All products fetched successfully", productDTOList));
    }

    @Operation( summary = "Delete multiple products", description = "This endpoint deletes all products with given product ids.")
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse<?>> deleteMultipleProduct(@RequestBody List<String> productIds){
        logger.info("Start deleting all products with id : {}", productIds);
        productService.deleteMultipleProduct(productIds);
        return ResponseEntity.status(HttpStatus.OK)
        .body(new ApiResponse<>(true, "Deleted multiple product ids", null));
    }
    
}