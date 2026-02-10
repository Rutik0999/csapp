package com.csapp.cloudcode.services.impl;

import com.csapp.cloudcode.entities.Customer;
import com.csapp.cloudcode.entities.Issue;
import com.csapp.cloudcode.exception.ResourceNotFoundException;
import com.csapp.cloudcode.pyaloads.CustomerDto;
import com.csapp.cloudcode.pyaloads.IssueDto;
import com.csapp.cloudcode.repo.CustomerRepo;
import com.csapp.cloudcode.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if (customer.isPresent()) {
            return null;
        } else {
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
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer",
                        customerId,
                        "Not available in db"
                ));
        return this.customerToDto(customer);
    }

    @Override
    public List<CustomerDto> getAllCustomer() {
        List<Customer> listofCustomer = this.customerRepo.findAll();
        List<CustomerDto> listOfCustomerDto = listofCustomer.stream()
                .map((Customer customer) -> this
                        .customerToDto(customer)).toList();

        return listOfCustomerDto;
    }

    @Override
    public void deleteCustomerById(String customerId) {
        Customer customer = this.customerRepo.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Customer",
                        customerId,
                        "Not found"
                ));

        this.customerRepo.delete(customer);
    }


    public CustomerDto customerToDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setCustomerEmail(customer.getCustomerEmail());
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setAbout(customer.getAbout());
        customerDto.setPassword(customer.getPassword());
        customerDto.setAddress(customer.getAddress());
        List<IssueDto> listOfIssueDto = customer.getListOfIssue()
                .stream().map(this::issueToDto).toList();
        customerDto.setListOfIssueDto(listOfIssueDto);
        return customerDto;
    }

    public Customer dtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setCustomerId(customerDto.getCustomerId());
        customer.setCustomerEmail(customerDto.getCustomerEmail());
        customer.setCustomerName(customerDto.getCustomerName());
        customer.setAbout(customerDto.getAbout());
        customer.setPassword(customerDto.getPassword());
        customer.setAddress(customerDto.getAddress());
        List<Issue> listOfIssue = customerDto.getListOfIssueDto()
                .stream().map(issueDto-> {
                    Issue issue = this.dtoToIssue(issueDto);
                    issue.setCustomer(customer);
                    return issue;
                }).toList();
        customer.setListOfIssue(listOfIssue);

        return customer;
    }

    public Issue dtoToIssue(IssueDto issueDto){
        Issue issue = new Issue();
        issue.setIssueId(issueDto.getIssueId());
        issue.setIssueDescription(issueDto.getIssueDescription());
        issue.setIssueTitle(issueDto.getIssueTitle());
        issue.setCreatedAt(issueDto.getCreatedAt());
        issue.setUpdatedAt(LocalDateTime.now());
        issue.setStatus(issueDto.getStatus());

        return issue;
    }

    public IssueDto issueToDto (Issue issue){
        IssueDto issueDto = new IssueDto();
        issueDto.setIssueId(issue.getIssueId());
        issueDto.setIssueDescription(issue.getIssueDescription());
        issueDto.setIssueTitle(issue.getIssueTitle());
        issueDto.setCreatedAt(issue.getCreatedAt());
        issueDto.setUpdatedAt(issue.getUpdatedAt());
        issueDto.setStatus(issue.getStatus());

        return issueDto;
    }

}