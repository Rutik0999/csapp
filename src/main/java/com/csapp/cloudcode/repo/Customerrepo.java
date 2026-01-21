package com.csapp.cloudcode.repo;

import com.csapp.cloudcode.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Customerrepo extends JpaRepository<Customer, Integer> {
}
