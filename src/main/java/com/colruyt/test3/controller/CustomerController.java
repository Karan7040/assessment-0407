package com.colruyt.test3.controller;

import com.colruyt.test3.service.CustomerService;
import com.colruyt.test3.model.CustomerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    final CustomerService customerService;

    @GetMapping("/fetchcustomers")
    public List<CustomerDto> getAllCustomers()
    {
        return customerService.fetchCustomers();
    }

    @GetMapping("/fetchById/{id}")
    public String getCustomerFirstName(@PathVariable String id)
    {
        return customerService.getById(id);
    }
}
