package com.csapp.cloudcode.controller;

import com.csapp.cloudcode.pyaloads.CustomerDto;
import com.csapp.cloudcode.pyaloads.GlobalApiResponse;
import com.csapp.cloudcode.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer/api")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("create")
    public ResponseEntity<GlobalApiResponse> registerCustomer(@RequestBody CustomerDto customerDto) {
        CustomerDto createdCustomer = this.customerService.createCustomer(customerDto);
        if (createdCustomer == null) {
            GlobalApiResponse globalApiResponse = new GlobalApiResponse(
                    false,
                    "cannot create customer",
                    createdCustomer,
                    HttpStatus.BAD_REQUEST.toString()
            );
            return new ResponseEntity<GlobalApiResponse>(globalApiResponse, HttpStatus.BAD_REQUEST);
        }
        GlobalApiResponse globalApiResponse = new GlobalApiResponse(
                true,
                "user created successfully",
                createdCustomer,
                HttpStatus.CREATED.toString()
        );
        return new ResponseEntity<>(globalApiResponse, HttpStatus.CREATED);
    }

    @PostMapping("update/customer/{customerId}")
    public ResponseEntity<GlobalApiResponse> updateCustomer(
            @PathVariable(name = "customerId") String customerId,
            @RequestBody CustomerDto CustomerDto) {
        CustomerDto updatedCustomer = this.customerService.updateCustomer(customerId, CustomerDto);
        if (updatedCustomer == null) {
            GlobalApiResponse globalApiResponse = new GlobalApiResponse(
                    false,
                    "cannot update customer",
                    null,
                    HttpStatus.BAD_REQUEST.toString()
            );
            return new ResponseEntity<GlobalApiResponse>(globalApiResponse, HttpStatus.BAD_REQUEST);
        }
        GlobalApiResponse globalApiResponse = new GlobalApiResponse(
                true,
                "customer updated successfully",
                updatedCustomer,
                HttpStatus.CREATED.toString()
        );
        return new ResponseEntity<>(globalApiResponse, HttpStatus.CREATED);
    }

    @GetMapping("getCustomer/{customerId}")
    public ResponseEntity<GlobalApiResponse> getCustomerById(@PathVariable(name = "customerId") String customerId) {
        CustomerDto customerDto = this.customerService.getCustomerById(customerId);
        GlobalApiResponse globalApiResponse = new GlobalApiResponse(
                true,
                "customer fetched Successfully",
                customerDto,
                HttpStatus.OK.toString()
        );
        return new ResponseEntity<GlobalApiResponse>(globalApiResponse, HttpStatus.OK);
    }

    @GetMapping("getAllCustomer")
    public ResponseEntity<GlobalApiResponse> getAllCustomer() {
        List<CustomerDto> customerDtoList = this.customerService.getAllCustomer();
        GlobalApiResponse globalApiResponse = new GlobalApiResponse(
                true,
                "customerList fetched Successfully",
                customerDtoList,
                HttpStatus.OK.toString()
        );
        return new ResponseEntity<GlobalApiResponse>(globalApiResponse, HttpStatus.OK);
    }

    @DeleteMapping("delete/customer/{customerId}")
    public ResponseEntity<GlobalApiResponse> deleteCustomer(
            @PathVariable String customerId) {

        this.customerService.deleteCustomerById(customerId);

        GlobalApiResponse response = new GlobalApiResponse(
                true,
                "Customer deleted successfully",
                null,
                HttpStatus.OK.toString()
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}

