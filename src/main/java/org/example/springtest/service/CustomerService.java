package org.example.springtest.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.springtest.exception.CustomerNotFoundException;
import org.example.springtest.model.Customer;
import org.example.springtest.model.dto.CustomerDto;
import org.example.springtest.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class CustomerService {
    @Autowired
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

    public CustomerDto getCustomerById(Integer id){
            Customer customer = customerRepository.findById(id).orElseThrow(() -> new CustomerNotFoundException("customer Not Found"));
            return CustomerDto.builder().custId(customer.getCustId()).address(customer.getAddress()).firstName(customer.getFirstName()).lastName(customer.getLastName()).build();
    }

}
