package com.colruyt.practicemockito.controller;

import com.colruyt.practicemockito.model.CustomerModel;
import com.colruyt.practicemockito.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/customers")

public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @GetMapping("/{custId}")
    public CustomerModel getCustomerById(@PathVariable Long custId) {
        return customerService.getCustomerById(custId);
    }
}
