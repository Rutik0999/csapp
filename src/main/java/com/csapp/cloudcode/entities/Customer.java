package com.csapp.cloudcode.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;


    @Column(name = "customer_name")
    private String customerName;
    private String customerEmail;
    private String password;
    private String address;
    @Column(name = "about_customer")

    private String about;
}