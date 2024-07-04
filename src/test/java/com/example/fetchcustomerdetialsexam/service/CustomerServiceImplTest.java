package com.example.fetchcustomerdetialsexam.service;

import entity.CustomerEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.stereotype.Repository;
import repository.CustomerRepository;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Test
    void test() {


        Mockito.when(CustomerRepository.findById(1)).thenReturn(Optional.of(new CustomerEntity()));
        CustomerEntity passengerEntity = customerService.getCustomerById(1);
        assertThat(passengerEntity).isNotNull();
        assertThat(passengerEntity.getCustId()).isNull();
        verify(customerRepository, times(1)).findById(1);
    }





}