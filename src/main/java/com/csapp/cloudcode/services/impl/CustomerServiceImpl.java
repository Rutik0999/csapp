package com.csapp.cloudcode.services.impl;

import com.csapp.cloudcode.entities.Customer;
import com.csapp.cloudcode.exception.ResourceNotFoundException;
import com.csapp.cloudcode.pyaloads.CustomerDto;
import com.csapp.cloudcode.repo.CustomerRepo;
import com.csapp.cloudcode.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        Optional<Customer> customer = this.customerRepo.findByCustomerEmail(customerDto.getCustomerEmail());
        if(customer.isPresent()){
            return null;
        }else{
            Customer customerObj = this.dtoToCustomer(customerDto);
            Customer savedCustomer = this.customerRepo.save(customerObj);
            return this.customerToDto(savedCustomer);
        }
    }

    @Override
    public CustomerDto updateCustomer(String customerId, CustomerDto customerDto) {
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "customer",
                        customerId,
                        "not found"));

        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setAbout(customerDto.getAbout());
        customer.setPassword(customerDto.getPassword());
        customer.setAddress(customerDto.getAddress());

        Customer savedCustomer = this.customerRepo.save(customer);
        return this.customerToDto(savedCustomer);
    }

    @Override
    public CustomerDto getCustomerById(String customerId) {
        return null;
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        return List.of();
    }

    @Override
    public void deleteCustomerById(String customerId) {

    }

    public CustomerDto customerToDto(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setCustomerEmail(customer.getCustomerEmail());
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setAbout(customer.getAbout());
        customerDto.setPassword(customer.getPassword());
        customerDto.setAddress(customer.getAddress());
        return customerDto;
    }
    public Customer dtoToCustomer(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setAbout(customerDto.getAbout());
        customer.setPassword(customerDto.getPassword());
        customer.setAddress(customerDto.getAddress());
        return customer;
    }

}