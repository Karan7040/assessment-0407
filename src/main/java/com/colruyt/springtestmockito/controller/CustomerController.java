package com.colruyt.springtestmockito.controller;

import com.colruyt.springtestmockito.model.dto.CustomerDto;
import com.colruyt.springtestmockito.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class CustomerController {
    private CustomerService customerService;


    @GetMapping("/getCustomer/{id}")
    public ResponseEntity<CustomerDto> info(@PathVariable Integer id){
        return new ResponseEntity<>(customerService.fetchCustomerDetails(id), HttpStatus.OK);

    }


}
