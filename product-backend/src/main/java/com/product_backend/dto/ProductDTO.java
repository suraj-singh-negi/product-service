package com.product_backend.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class ProductDTO {
    private String productId;

    private String productName;

    private String productDescription;

    private byte[] productImage;
    
}
