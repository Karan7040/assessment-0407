package com.colruyt.test3.service;

import com.colruyt.test3.model.CustomerDto;
import com.colruyt.test3.model.entity.Customer;
import com.colruyt.test3.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {
    final CustomerRepository customerRepository;
    public List<CustomerDto> fetchCustomers()
    {
        List<Customer> customerList=customerRepository.findAll();

        return customerList.stream().map(customer ->{
            return CustomerDto.builder()
                    .address(customer.getAddress())
                    .custId(customer.getCustId())
                    .firstName(customer.getFirstName())
                    .lastName(customer.getLastName())
                    .build();
                }

                ).collect(Collectors.toList());

    }

    public String getById(String id) {
        Customer customer=customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);

        return customer.getFirstName();
    }
}
