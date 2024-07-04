package com.example.fetchcustomerdetialsexam.controller;

import com.example.fetchcustomerdetialsexam.model.CustomerDto;
import com.example.fetchcustomerdetialsexam.service.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

   private final CustomerServiceImpl customerService;


    @GetMapping("/getcustomerdetails/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}