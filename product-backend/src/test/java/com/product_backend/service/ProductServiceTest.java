package com.product_backend.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.security.SecureRandom;

import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.product_backend.dto.ProductDTO;
import com.product_backend.entity.Product;
import com.product_backend.mapper.ProductMapper;
import com.product_backend.repository.ProductRepository;

import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {
    
    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private ProductMapper productMapper;

    @Test
    public void createdProductTest(){
        

        String productName = "testProductName";
        String productDescription = "testProductDescription";

        byte[] productImage = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(productImage);
         
        ProductDTO productDTO = ProductDTO.builder()
                                        .productName(productName)
                                        .productDescription(productDescription)
                                        .productImage(productImage)
                                        .build();
        
        when(productMapper.toEntity(any(ProductDTO.class))).thenReturn(new Product());
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);
        when(productRepository.save(any(Product.class))).thenReturn(new Product());
        
        ProductDTO resultProductDTO = productService.createdProduct(productDTO);
        assertEquals(productName, resultProductDTO.getProductName());
        assertEquals(productDescription, resultProductDTO.getProductDescription());
        assertArrayEquals(productImage, resultProductDTO.getProductImage());                                
    }

}
