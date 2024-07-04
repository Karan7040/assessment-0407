package org.example.springtest;

import com.github.database.rider.junit5.api.DBRider;
import org.example.springtest.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DBRider
class SpringtestApplicationTests {
	@Autowired
	WebApplicationContext context;
	private CustomerRepository customerRepository;

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
	void whenCustomerNotFound_throwError() {

		Mockito.doThrow(new IllegalArgumentException("customer not found")).when(customerRepository).existsById(1);

		assertThatThrownBy(() -> customerRepository.existsById(1))
				.isInstanceOf(IllegalArgumentException.class)
				.hasMessage("id not found");
	}
}
