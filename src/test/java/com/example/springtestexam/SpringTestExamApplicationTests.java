package com.example.springtestexam;

import com.example.springtestexam.model.dto.CustomerDto;
import com.example.springtestexam.model.entity.Customer;
import com.example.springtestexam.repository.CustomerRepository;
import com.example.springtestexam.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringTestExamApplicationTests {
    @InjectMocks
    private CustomerService customerService;
    @Mock
    private CustomerRepository customerRepository;
    @Autowired
    private MockMvc mockMvc;
    protected static final ObjectMapper objectMapper = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    @Test
    public void givenCustomerId_whenFetchingDetails_thenReturnsDetails() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/customers/34");
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        resultActions.andExpect(status().isOk());
        Mockito.when(customerRepository.findById("34"))
                .thenReturn(Optional.of(new Customer("34","Entry","Last","Kanas")));
        CustomerDto customer=customerService.getCustomerDetails("34");
        Assertions.assertThat(customer).isNotNull();


    }

    @Test
    public void givenCustomerId_whenFetchingDetails_thenThrowException() throws Exception {
        MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/customers/1");
        ResultActions resultActions = mockMvc.perform(mockHttpServletRequestBuilder);
        resultActions.andExpect(status().isNotFound());
        String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
        ProblemDetail problemDetail = objectMapper.readValue(contentAsString, ProblemDetail.class);
        assertEquals(problemDetail.getDetail(),"Customer Not Found");

    }


}
