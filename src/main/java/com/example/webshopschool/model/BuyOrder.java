package com.example.webshopschool.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Entity
@Data
public class BuyOrder {

    @Id
    @SequenceGenerator(
            name = "buyOrder_sequence",
            sequenceName = "buyOrder_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "buyOrder_sequence"
    )
    private Long id;
    private String orderNumber;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    private Customer customer;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="buyorder_product_map",
            joinColumns = @JoinColumn(
                    referencedColumnName ="id")
            ,
            inverseJoinColumns = @JoinColumn(
                    referencedColumnName ="id"
            )
    )
    private List<Product> products;

    public BuyOrder() {
    }

    public BuyOrder(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public BuyOrder(String orderNumber, Customer customer) {
        this.orderNumber = orderNumber;
        this.customer = customer;
    }

    public BuyOrder(String orderNumber, Customer customer, List<Product> products) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.products = products;
    }

    public BuyOrder(Long id, String orderNumber, Customer customer, List<Product> products) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.products = products;
    }

    public void setProductList(Product p){
        this.products = Arrays.asList(p);
    }
}
