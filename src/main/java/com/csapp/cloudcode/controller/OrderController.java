package com.csapp.cloudcode.controller;

import com.csapp.cloudcode.pyaloads.GlobalApiResponse;
import com.csapp.cloudcode.pyaloads.OrderDto;
import com.csapp.cloudcode.services.impl.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order/api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/create")
    public ResponseEntity<GlobalApiResponse> createOrder(@RequestBody OrderDto orderDto) {

        OrderDto createdOrder = orderService.createOrder(orderDto);

        return new ResponseEntity<>(
                new GlobalApiResponse(true, "Order created successfully", createdOrder, HttpStatus.CREATED.toString()),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update/{orderId}")
    public ResponseEntity<GlobalApiResponse> updateOrder(
            @PathVariable UUID orderId,
            @RequestBody OrderDto orderDto) {

        OrderDto updatedOrder = orderService.updateOrder(orderId, orderDto);

        return ResponseEntity.ok(
                new GlobalApiResponse(true, "Order updated successfully", updatedOrder, HttpStatus.OK.toString())
        );
    }

    @GetMapping("/get/{orderId}")
    public ResponseEntity<GlobalApiResponse> getOrder(@PathVariable UUID orderId) {

        OrderDto order = orderService.getOrderById(orderId);

        return ResponseEntity.ok(
                new GlobalApiResponse(true, "Order fetched successfully", order, HttpStatus.OK.toString())
        );
    }

    @GetMapping("/getAll")
    public ResponseEntity<GlobalApiResponse> getAllOrders() {

        List<OrderDto> orders = orderService.getAllOrders();

        return ResponseEntity.ok(
                new GlobalApiResponse(true, "Orders fetched successfully", orders, HttpStatus.OK.toString())
        );
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<GlobalApiResponse> deleteOrder(@PathVariable UUID orderId) {

        orderService.deleteOrderById(orderId);

        return ResponseEntity.ok(
                new GlobalApiResponse(true, "Order deleted successfully", null, HttpStatus.OK.toString())
        );
    }
}
