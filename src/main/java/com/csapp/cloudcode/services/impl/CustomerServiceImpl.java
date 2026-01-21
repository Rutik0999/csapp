package com.csapp.cloudcode.services.impl;

import com.csapp.cloudcode.entities.Customer;
import com.csapp.cloudcode.repo.Customerrepo;
import com.csapp.cloudcode.services.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private Customerrepo customerRepo;

    @Override
    public Customer createCustomer(Customer customer) {
        Customer savedCustomer = this.customerRepo.save(customer);
        return savedCustomer;
    }
}
