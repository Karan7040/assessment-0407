package org.example.springtest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.github.database.rider.junit5.api.DBRider;
import org.example.springtest.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ProblemDetail;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
class SpringtestApplicationTests {
	@Autowired
	WebApplicationContext context;

	@Autowired
	private CustomerRepository customerRepository;


	protected static final ObjectMapper objectMapper = new ObjectMapper()
			.registerModule(new JavaTimeModule());

	private MockMvc getMockMvc() throws Exception {
		return MockMvcBuilders.webAppContextSetup(context).build();
	}

	@Test
	void whenGivenNothing_returnCustomerDetails() throws Exception {
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/get_customer");
		ResultActions resultActions = this.getMockMvc().perform(mockHttpServletRequestBuilder);
		resultActions.andExpect(status().isOk());
	}

	@Test
	void givenIdAndSource_WhenAskedForTicketWithInvalidId_returnException() throws Exception{
		MockHttpServletRequestBuilder mockHttpServletRequestBuilder = MockMvcRequestBuilders.get("/get_customer/31");
		ResultActions resultActions = this.getMockMvc().perform(mockHttpServletRequestBuilder);
		resultActions.andExpect(status().isNotFound());
		String contentAsString = resultActions.andReturn().getResponse().getContentAsString();
		ProblemDetail problemDetail = objectMapper.readValue(contentAsString, ProblemDetail.class);
		assertEquals("customer Not Found", problemDetail.getDetail());
	}
}
