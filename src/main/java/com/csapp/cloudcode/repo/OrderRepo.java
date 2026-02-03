package com.csapp.cloudcode.repo;

import com.csapp.cloudcode.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepo extends JpaRepository<Order,UUID> {
    Optional<Order> findByOrderId(UUID orderId);
}
