package com.csapp.cloudcode.services.impl;

import com.csapp.cloudcode.pyaloads.OrderDto;

import java.util.List;
import java.util.UUID;

public interface OrderService {

    OrderDto createOrder(OrderDto orderDto);

    OrderDto updateOrder(UUID orderId, OrderDto orderDto);

    OrderDto getOrderById(UUID orderId);

    List<OrderDto> getAllOrders();

    void deleteOrderById(UUID orderId);
}
