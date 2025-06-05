package com.product_backend.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.security.SecureRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.product_backend.dto.ProductDTO;
import com.product_backend.response.ApiResponse;
import com.product_backend.service.ProductService;

@RunWith(MockitoJUnitRunner.class)
public class ProductControllerTest {
    
    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService productService;

    private String productId = "testProductId";
    private String productName = "testProductName";
    private String productDescription = "testProductDescription";
    private byte[] productImage;

    @Before
    public void setUp(){
        productImage = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(productImage);
    }

    @Test
    public void createProductTest(){
        String message = "Product created successfully";

        when(productService.createdProduct(any(ProductDTO.class))).thenReturn(getOutputProductDTO());
        ResponseEntity<ApiResponse<?>> response = productController.createProduct(getInputProductDTO());
        ProductDTO responseProductDTO = (ProductDTO)response.getBody().getData();
        assertNotNull(response);
        assertTrue(response.getBody().isSuccess());
        assertEquals(message, response.getBody().getMessage());
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(productId, responseProductDTO.getProductId());
        assertEquals(productName, responseProductDTO.getProductName());
        assertEquals(productDescription, responseProductDTO.getProductDescription());
        assertArrayEquals(productImage, responseProductDTO.getProductImage());
    }

    @Test
    public void deleteProductTest(){
        String message = productId+" deleted";

        doNothing().when(productService).deleteProduct(anyString());
        ResponseEntity<ApiResponse<?>> response = productController.deleteProduct(productId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getBody().isSuccess());
        assertEquals(message, response.getBody().getMessage());
    }

    private ProductDTO getOutputProductDTO(){
        return ProductDTO.builder()
                            .productId(productId)
                            .productName(productName)
                            .productDescription(productDescription)
                            .productImage(productImage)
                            .build();
    }

    private ProductDTO getInputProductDTO(){
        return ProductDTO.builder()
                            .productName(productName)
                            .productDescription(productDescription)
                            .productImage(productImage)
                            .build();
    }

}
