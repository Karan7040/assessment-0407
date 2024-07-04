package com.colruyt.test3;

import com.colruyt.test3.model.entity.Customer;
import com.colruyt.test3.repository.CustomerRepository;
import com.colruyt.test3.service.CustomerNotFoundException;
import com.colruyt.test3.service.CustomerService;
import com.colruyt.test3.model.CustomerDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.database.rider.core.api.dataset.DataSet;
import com.github.database.rider.junit5.api.DBRider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.internal.verification.VerificationModeFactory.atLeastOnce;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
@ExtendWith(MockitoExtension.class)
class Test3ApplicationTests {

	@Autowired
	MockMvc mockMvc;

	protected static final ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

	@InjectMocks
	CustomerService customerService;
	@Mock
	CustomerRepository customerRepository;
// todo: tests are failing
	@Test
	@DataSet("dataset/customer.json")
	void given_nothing_check_the_correct_output_allpassenger() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/fetchById/11");
		MvcResult mvcResult = mockMvc.perform(mockHttpServletRequestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		String responseContentJson = response.getContentAsString();
		assertThat(responseContentJson ).isEqualTo("John");

	}

	@Test
	public void getCheck() {
		Mockito.when(customerRepository.findById("11"))
				.thenReturn(Optional.of(new Customer()));
		String string = customerService.getById("11");
		assertThat(string).isNull();


	}
}
