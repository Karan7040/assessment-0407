package com.example.test_4_7_24.controller;

import com.example.test_4_7_24.model.entity.CustomerEntity;
import com.example.test_4_7_24.service.CustomerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DBRider
class CustomerDetailsTest {
    @Autowired
    MockMvc mockMvc;
    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    private CustomerService customerService;

    @InjectMocks
    private CustomerDetailsController customerDetailsController;

    //Unit Testing
    @Test
    public void giveCustomerId_whenAskedForCustomerDetails_thenReturnCustomerDetails() throws Exception {

        CustomerEntity results1 = CustomerEntity.builder().customerid(1234)
                .customeraddress("NewYork").customerfirstname("Dinesh")
                .customerlastname("Kumar").build();

        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/customerdetails/1234");
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        resultActions.andExpect(status().isOk());
                resultActions.andExpect(content()
                .json(objectMapper.writeValueAsString(results1)));
    }

    //Integrated Testing


}