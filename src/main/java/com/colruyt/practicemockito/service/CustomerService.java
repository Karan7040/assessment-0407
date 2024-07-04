package com.colruyt.practicemockito.service;

import com.colruyt.practicemockito.exception.CustomerNotFoundException;
import com.colruyt.practicemockito.model.CustomerModel;
import com.colruyt.practicemockito.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    public CustomerModel getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new CustomerNotFoundException("Customer not found with id: " + customerId));
    }
}
