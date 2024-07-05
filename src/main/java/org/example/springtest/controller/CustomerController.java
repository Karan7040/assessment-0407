package org.example.springtest.controller;

import org.example.springtest.model.dto.CustomerDto;
import org.example.springtest.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/get_customer")
    public List<CustomerDto> getCustomer(){
        return customerService.getCustomer();
    }

    @GetMapping("/get_customer/{id}")
    public CustomerDto getCustomerById(@PathVariable(name = "id") Integer id){
        return customerService.getCustomerById(id);
    }

}
