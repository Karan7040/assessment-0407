package com.example.fetchcustomerdetialsexam.service;

import com.example.fetchcustomerdetialsexam.exception.CustomerNotFoundException;
import entity.CustomerEntity;
import org.springframework.stereotype.Service;
import repository.CustomerRepository;

@Service
public class CustomerServiceImpl {

   public CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }



    public CustomerEntity getCustomerById(Integer id) {

        if (customerRepository.findById(1).isEmpty()) {
                throw new CustomerNotFoundException();
        }
        return null;
    }

}
