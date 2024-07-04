package com.example.springtestexam.controller;

import com.example.springtestexam.exception.CustomerNotFoundExcetpion;
import com.example.springtestexam.model.dto.CustomerDto;
import com.example.springtestexam.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers/{id}")
    public CustomerDto getCustomers(@PathVariable("id")String customerId)
    {

            return customerService.getCustomerDetails(customerId);

    }
}
