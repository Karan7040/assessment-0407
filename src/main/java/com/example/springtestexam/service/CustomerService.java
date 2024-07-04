package com.example.springtestexam.service;

import com.example.springtestexam.exception.CustomerNotFoundExcetpion;
import com.example.springtestexam.model.dto.CustomerDto;
import com.example.springtestexam.model.entity.Customer;
import com.example.springtestexam.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    // todo : make customerRepository final
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public CustomerDto getCustomerDetails(String customerId) {
        Customer customer= customerRepository.findById(customerId).orElseThrow(()->new CustomerNotFoundExcetpion("Customer Not Found"));
        return CustomerDto.builder().custId(customer.getCustId()).firstName(customer.getFirstName()
                ).lastName(customer.getLastName()).address(customer.getAddress()).build();

    }
}
