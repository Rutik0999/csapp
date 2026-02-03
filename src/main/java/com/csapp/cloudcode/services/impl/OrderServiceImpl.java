package com.csapp.cloudcode.services.impl;

import com.csapp.cloudcode.entities.Order;
import com.csapp.cloudcode.exception.ResourceNotFoundException;
import com.csapp.cloudcode.pyaloads.OrderDto;
import com.csapp.cloudcode.repo.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepo orderRepo;

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        Order order = new Order();
        order.setCustomerName(orderDto.getCustomerName());
        order.setOrderLocation(orderDto.getOrderLocation());
        order.setAddress(orderDto.getAddress());
        order.setOrderRelatedQuery(orderDto.getOrderRelatedQuery());
        order.setPaymentMethod(orderDto.getPaymentMethod());

        order.setOrderStatus("CREATED");
        order.setPaymentStatus("PENDING");
        order.setOrderDateTime( LocalDateTime.now());

        Order savedOrder = orderRepo.save(order);
        return orderToDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(UUID orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order", "OrderId", orderId.toString()));

        return orderToDto(order);
    }


    @Override
    public OrderDto updateOrder(UUID orderId, OrderDto orderDto) {

        Order order = this.orderRepo.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Order",
                        "OrderId",
                        orderId.toString()));

        order.setCustomerName(orderDto.getCustomerName());
        order.setOrderStatus(orderDto.getOrderStatus());
        order.setOrderLocation(orderDto.getOrderLocation());
        order.setOrderRelatedQuery(orderDto.getOrderRelatedQuery());
        order.setAddress(orderDto.getAddress());
        order.setPaymentMethod(orderDto.getPaymentMethod());
        order.setPaymentStatus(orderDto.getPaymentStatus());
        order.setOrderDateTime(orderDto.getOrderDateTime());

        Order saveOrder = this.orderRepo.save(order);
        return this.orderToDto(saveOrder);
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> listOfOrder = this.orderRepo.findAll();
        List<OrderDto> listOfOrderDto = listOfOrder.stream()
                .map((Order order)->this.orderToDto(order)).toList();

        return listOfOrderDto;
    }

    @Override
    public void deleteOrderById(UUID orderId) {
        Order order = this.orderRepo.findById(orderId)
                .orElseThrow( ()-> new ResourceNotFoundException(
                        "Order",
                        "OrderId",
                        orderId.toString()
                ));
        this.orderRepo.delete(order);
    }

    private Order dtoToOrder(OrderDto dto) {
        Order order = new Order();
        order.setCustomerName(dto.getCustomerName());
        order.setOrderLocation(dto.getOrderLocation());
        order.setOrderStatus(dto.getOrderStatus());
        order.setAddress(dto.getAddress());
        order.setOrderRelatedQuery(dto.getOrderRelatedQuery());
        order.setOrderDateTime(dto.getOrderDateTime());
        order.setPaymentMethod(dto.getPaymentMethod());
        order.setPaymentStatus(dto.getPaymentStatus());
        return order;
    }

    private OrderDto orderToDto(Order order) {
        OrderDto dto = new OrderDto();
        dto.setOrderId(order.getOrderId());
        dto.setCustomerName(order.getCustomerName());
        dto.setOrderLocation(order.getOrderLocation());
        dto.setOrderStatus(order.getOrderStatus());
        dto.setAddress(order.getAddress());
        dto.setOrderRelatedQuery(order.getOrderRelatedQuery());
        dto.setOrderDateTime(order.getOrderDateTime());
        dto.setPaymentMethod(order.getPaymentMethod());
        dto.setPaymentStatus(order.getPaymentStatus());
        return dto;
    }
}
