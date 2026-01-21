package com.csapp.cloudcode.controller;

import com.csapp.cloudcode.entities.Customer;
import com.csapp.cloudcode.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer/api")
public class UserController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {

        Customer createdCustomer = customerService.createCustomer(customer);

        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }
}
