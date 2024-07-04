package com.example.test_4_7_24.service;

import com.example.test_4_7_24.model.dto.CustomerDto;

public interface Service {
    // todo : be more specific to your naming
    CustomerDto fetchCustomerDetailsById(Integer id);
}
