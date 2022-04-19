package com.example.webshopschool.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Customer {

    @Id
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Long id;

    private String name;
    private String address;

    /*@OneToOne(
            mappedBy = "customer"
    )
    private Membership membership;*/

    /*@JsonIgnore
    @OneToMany(mappedBy = "customer")
    private List<BuyOrder> orders;*/

    public Customer() {
    }

    public Customer(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Customer(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
}
