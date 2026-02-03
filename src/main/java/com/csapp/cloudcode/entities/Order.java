package com.csapp.cloudcode.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "tbl_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID orderId;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String address;

    private String orderLocation;

    private String orderRelatedQuery;

    private String orderStatus;

    private String paymentMethod;

    private String paymentStatus;

    @Column(name = "order_date_time")
    private LocalDateTime orderDateTime;
}