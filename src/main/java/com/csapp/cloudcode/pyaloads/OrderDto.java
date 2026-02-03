package com.csapp.cloudcode.pyaloads;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    @Nullable
    private UUID orderId;


    private String customerName;

    private String orderLocation;
    private String orderStatus;
    private String address;
    private String orderRelatedQuery;
    private LocalDateTime orderDateTime;
    private String paymentMethod;
    private String paymentStatus;
}