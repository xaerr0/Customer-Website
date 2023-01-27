package com.springboot.customerwebsite.repositories;


import com.springboot.customerwebsite.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

//    List<Customer> findAllById();
}