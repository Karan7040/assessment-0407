package org.example.springtest.service;

import org.example.springtest.model.Customer;
import org.example.springtest.model.dto.CustomerDto;
import org.example.springtest.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    public List<CustomerDto> getCustomer(){
        List<Customer> customer =  customerRepository.findAll();
        return customer.stream().map(customer1 -> CustomerDto.builder()
                .custId(customer1.getCustId())
                .address(customer1.getAddress())
                .firstName(customer1.getFirstName())
                .lastName(customer1.getLastName())
                .build()).collect(Collectors.toList());
    }

}
