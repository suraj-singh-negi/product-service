package com.product.product_backend.dto;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Lob;

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
