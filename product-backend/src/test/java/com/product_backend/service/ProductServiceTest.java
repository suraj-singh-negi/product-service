package com.product_backend.service;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

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

    @Test
    public void deleteProductTest(){
        String testProductId = "testProductId";
        productService.deleteProduct(testProductId);
        verify(productRepository, times(1)).deleteById(testProductId);
    }

    @Test
    public void getProductTest(){
        String productId = "testProductId";
        String productName = "testProductName";
        String productDescription = "testProductDescription";
        byte[] productImage = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(productImage);

        ProductDTO productDTO = ProductDTO.builder()
                                        .productId(productId)
                                        .productName(productName)
                                        .productDescription(productDescription)
                                        .productImage(productImage)
                                        .build();

        when(productRepository.findById(any())).thenReturn(Optional.of(new Product()));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        assertEquals(productId, productDTO.getProductId());
        assertEquals(productName, productDTO.getProductName());
        assertEquals(productDescription, productDTO.getProductDescription());
        assertEquals(productImage, productDTO.getProductImage());
    }

    @Test
    public void updateProductTest(){
        String productId = "testProductId";
        String productName = "testProductName";
        String productDescription = "testProductDescription";
        byte[] productImage = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(productImage);

        ProductDTO productDTO = ProductDTO.builder()
                                        .productId(productId)
                                        .productName(productName)
                                        .productDescription(productDescription)
                                        .productImage(productImage)
                                        .build();
        
        when(productRepository.save(any(Product.class))).thenReturn(new Product());
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        assertEquals(productId, productDTO.getProductId());
        assertEquals(productName, productDTO.getProductName());
        assertEquals(productDescription, productDTO.getProductDescription());
        assertArrayEquals(productImage, productDTO.getProductImage());
    }

    @Test
    public void getAllProductsTest(){
        String productId = "testProductId";
        String productName = "testProductName";
        String productDescription = "productDescription";

        byte[] productImage = new byte[16];
        SecureRandom random = new SecureRandom();
        random.nextBytes(productImage);

        ProductDTO productDTO = ProductDTO.builder()
                                        .productId(productId)
                                        .productName(productName)
                                        .productDescription(productDescription)
                                        .productImage(productImage)
                                        .build();

        when(productRepository.findAll()).thenReturn(List.of(new Product()));
        when(productMapper.toDto(any(Product.class))).thenReturn(productDTO);

        List<ProductDTO> productDTOList = productService.getAllProducts();

        assertEquals(1, productDTOList.size());
        assertEquals(productId, productDTOList.get(0).getProductId());
        assertEquals(productName, productDTOList.get(0).getProductName());
        assertEquals(productDescription, productDTOList.get(0).getProductDescription());
        assertArrayEquals(productImage, productDTOList.get(0).getProductImage());
    }

    @Test
    public void deleteMultipleProductTest(){
        String testProductId1 = "testProductId1";
        String testProductId2 = "testProductId2";
        List<String> productIds = List.of(testProductId1, testProductId2);

        productService.deleteMultipleProduct(productIds);

        verify(productRepository, times(1)).deleteById(testProductId1);
        verify(productRepository, times(1)).deleteById(testProductId2);
    }

}
