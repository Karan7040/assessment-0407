package com.example.test_4_7_24.service;

import com.example.test_4_7_24.model.dto.CustomerDto;

public interface Service {
    CustomerDto fetchCustomerDetailsById(Integer id);
}
