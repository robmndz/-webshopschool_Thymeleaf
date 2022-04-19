package com.example.webshopschool.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Membership {

    @Id
    @SequenceGenerator(
            name = "membership_sequence",
            sequenceName = "membership_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "membership_sequence"
    )
    private Long id;
    private String membershipNumber;

    @OneToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "id"
    )
    private Customer customer;

    public Membership() {
    }

    public Membership(String membershipNumber) {
        this.membershipNumber = membershipNumber;
    }

    public Membership(String membershipNumber, Customer customer) {
        this.membershipNumber = membershipNumber;
        this.customer = customer;
    }
}
