package com.colruyt.practicemockito.service;

import com.colruyt.practicemockito.exception.CustomerNotFoundException;
import com.colruyt.practicemockito.model.CustomerModel;
import com.colruyt.practicemockito.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

class CustomerServiceTest {

        @Mock
        private CustomerRepository customerRepository;

        @InjectMocks
        private CustomerService customerService;

        @Test
        public void testGetCustomerById_Success() {
            CustomerModel customer = new CustomerModel();
            customer.setCustomerId(7L);
            customer.setFirstName("ragul");
            customer.setLastName("dheivam");

            when(customerRepository.findById(7L)).thenReturn(Optional.of(customer));

            CustomerModel result = customerService.getCustomerById(7L);

            assertNotNull(result);
            assertEquals("ragul", result.getFirstName());
            verify(customerRepository, times(1)).findById(7L);
        }

        @Test
        public void testGetCustomerById_NotFound() {
            when(customerRepository.findById(7L)).thenReturn(Optional.empty());

            assertThrows(CustomerNotFoundException.class, () -> customerService.getCustomerById(7L));
            verify(customerRepository, times(1)).findById(7L);
        }
    }
