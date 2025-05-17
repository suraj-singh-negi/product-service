package com.product.product_backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    @Column(name="product_id")
    private String productId;

    @Column(name="product_name")
    private String productName;

    private String productDescription;

    @Lob
    @Column(name="productImage", columnDefinition="BLOB")
    private byte[] productImage;
}
