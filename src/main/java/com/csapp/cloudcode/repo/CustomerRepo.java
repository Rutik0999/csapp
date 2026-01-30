package com.csapp.cloudcode.repo;

import com.csapp.cloudcode.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, String> {
    Optional<Customer> findByCustomerEmail(String customerEmail);
}