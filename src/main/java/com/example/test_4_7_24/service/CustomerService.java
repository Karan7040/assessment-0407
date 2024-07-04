package com.example.test_4_7_24.service;

import com.example.test_4_7_24.model.dto.CustomerDto;
import com.example.test_4_7_24.model.entity.CustomerEntity;
import com.example.test_4_7_24.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

@org.springframework.stereotype.Service
public class CustomerService implements Service{
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public CustomerDto fetchCustomerDetailsById(Integer id) {
        CustomerEntity customer = customerRepository.findById(id).orElseThrow(RuntimeException::new);
        return CustomerDto.builder().customerid(customer.getCustomerid())
                .customeraddress(customer.getCustomeraddress())
                .customerfirstname(customer.getCustomerfirstname())
                .customerlastname(customer.getCustomerlastname())
                .build();
    }
}
