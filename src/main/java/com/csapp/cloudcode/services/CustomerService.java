package com.csapp.cloudcode.services;

import com.csapp.cloudcode.entities.Customer;
import com.csapp.cloudcode.pyaloads.CustomerDto;

import java.util.List;
import java.util.UUID;

public interface CustomerService {
    CustomerDto createCustomer(CustomerDto customerDto);
    CustomerDto updateCustomer(String customerId, CustomerDto customerDto);
    CustomerDto getCustomerById(String customerId);
    List<CustomerDto> getAllCustomer();
    void deleteCustomerById(String customerId);
}