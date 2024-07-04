package org.example.springtest.controller;

import org.example.springtest.model.Customer;
import org.example.springtest.model.dto.CustomerDto;
import org.example.springtest.service.CustomerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {
    // todo: where is injection?
    private CustomerService customerService;

    @GetMapping("/get_customer")
    public List<CustomerDto> getCustomer(){
        return customerService.getCustomer();
    }

}
