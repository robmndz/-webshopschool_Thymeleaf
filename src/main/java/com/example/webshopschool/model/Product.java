package com.example.webshopschool.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {

    @Id
    @SequenceGenerator(
            name = "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    private Long id;
    private String name;
    private String productNumber;

    public Product() {
    }

    public Product(String name, String productNumber) {
        this.name = name;
        this.productNumber = productNumber;
    }

    public Product(Long id, String name, String productNumber) {
        this.id = id;
        this.name = name;
        this.productNumber = productNumber;
    }
}
