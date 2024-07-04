package com.colruyt.springtestmockito.service;

import com.colruyt.springtestmockito.exception.CustomerNotFoundException;
import com.colruyt.springtestmockito.model.dto.CustomerDto;
import com.colruyt.springtestmockito.model.entity.CustomerEntity;
import com.colruyt.springtestmockito.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor

public class CustomerService {
    private CustomerRepository customerRepository;


        public CustomerDto fetchCustomerDetails(Integer id) {
            Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(id);

            if (customerEntityOptional.isEmpty()) {
                throw new CustomerNotFoundException();
            } else {
                CustomerEntity customerEntity = customerEntityOptional.get();
                return CustomerDto.builder()
                        .custId(customerEntity.getCustId())
                        .address(customerEntity.getAddress())
                        .firstName(customerEntity.getFirstName())
                        .lastName(customerEntity.getLastName())
                        .build();
            }
        }
}







