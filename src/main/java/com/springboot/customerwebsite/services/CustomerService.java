package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.Customer;
import com.springboot.customerwebsite.repositories.CustomerRepo;

import java.util.List;

public interface CustomerService {
    List<Customer> getAllCustomers();

    List<Customer> getAllCustomersById(List<Long> idList);

    Customer saveCustomer(Customer customer);

    Customer getCustomer(Long id);

    void deleteCustomer(Long id);

    void deleteAllCustomers();

    List<Customer> saveAllCustomers(List<Customer> customerList);
}