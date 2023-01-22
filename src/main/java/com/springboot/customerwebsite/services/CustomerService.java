package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.Customer;
import com.springboot.customerwebsite.repositories.CustomerRepo;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    Customer saveCustomer(Customer customer);

    Customer getCustomer(Long id);

    void deleteCustomer(Long id);

    List<Customer> saveAllCustomers(List<Customer> customerList);
}