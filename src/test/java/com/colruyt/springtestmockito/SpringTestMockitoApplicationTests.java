package com.colruyt.springtestmockito;

import com.colruyt.springtestmockito.exception.CustomerNotFoundException;
import com.colruyt.springtestmockito.repository.CustomerRepository;
import com.colruyt.springtestmockito.service.CustomerService;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest
@DBRider
class SpringTestMockitoApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private CustomerRepository customerRepository;
    @InjectMocks
    private CustomerService customerService;


    @Test
    void whenProvidingwrongId() throws Exception {
        Mockito.when(customerRepository.findById(5)).thenThrow(new CustomerNotFoundException());
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/getCustomer/5");
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        resultActions.andExpect(status().isNotFound());
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        assertThat(contentAsString).isEqualTo("Customer Not Found");
    }


    @Test
    void whenIdPresent_ReturnsStatusOk() throws Exception {
        int custId = 87;
        mockMvc.perform(MockMvcRequestBuilders.get("/getCustomer/{id}", custId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }
}

















