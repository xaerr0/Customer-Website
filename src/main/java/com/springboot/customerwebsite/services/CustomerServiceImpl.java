package com.springboot.customerwebsite.services;

import com.springboot.customerwebsite.models.Customer;
import com.springboot.customerwebsite.repositories.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CustomerServiceImpl implements CustomerService  {

    @Autowired
    final CustomerRepo customerRepo;

    // The findAll function gets all the customers by doing a SELECT query in the DB.
    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    // The save function uses an INSERT query in the DB.
    @Override
    @Transactional
    public Customer saveCustomer(Customer customer) {
        return customerRepo.save(customer);
    }

    // The findById function uses a SELECT query with a WHERE clause in the DB.
    @Override
    public Customer getCustomer(Long id) {
        return customerRepo.findById(id)
                .orElse(null);
    }

    // The deleteById function deletes the customer by doing a DELETE in the DB.
    @Override
    @Transactional
    public void deleteCustomer(Long id) {
        customerRepo.deleteById(id);
    }

    // The saveAll function would do multiple INSERTS into the DB.
    @Override
    @Transactional
    public List<Customer> saveAllCustomers(List<Customer> customerList) {
        return customerRepo.saveAll(customerList);
    }

  }