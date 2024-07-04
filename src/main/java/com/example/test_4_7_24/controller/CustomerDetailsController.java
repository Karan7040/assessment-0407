package com.example.test_4_7_24.controller;

import com.example.test_4_7_24.model.dto.CustomerDto;
import com.example.test_4_7_24.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
//@RequestMapping("customer")
public class CustomerDetailsController {
    // todo : use constructor injection please
    @Autowired
    CustomerService customerService;

    @ResponseBody
    @GetMapping("/customerdetails/{id}")
    public CustomerDto getCustomerDetailsById(@PathVariable("id") Integer id)
    {
        return customerService.fetchCustomerDetailsById(id);
    }


}
