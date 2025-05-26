package com.product_backend.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "Product")
public class Product {
    @Id
    @Column(name="product_id")
    private String productId;

    @Column(name="product_name")
    private String productName;

    @Column(name="product_description")
    private String productDescription;

    @Lob
    @Column(name="productImage", columnDefinition="BLOB")
    private byte[] productImage;
}
