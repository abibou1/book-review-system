package dev.abibou.bookreview.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import dev.abibou.bookreview.payload.request.UserRequest;
import dev.abibou.bookreview.services.UserDetailsServiceImpl;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // avoid JUnitException
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private UserDetailsServiceImpl userService;
	
	private String signupURL = "/api/auth/signup";
	private String loginURL="/api/auth/login";
	
	@BeforeAll
	public void delete_jonhD_username() {
		String username = "johnD";
		
		userService.deleteUser(username);
	}
	
	/* 
	 * good user:
	 * bad user: 
	 */
	
	@Test
	public void signup_shouldRegisterAdmin_whenDataIsValid() throws Exception {
		
		UserRequest user = new UserRequest("johnD", "1234567", "ADMIN");
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(signupURL)
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(status, HttpStatus.CREATED.value());

	}
	
	@Test
	public void signup_shouldReturBadRequest_whenDataIsInvalid() throws Exception {
UserRequest user = new UserRequest("amb", "1234567", "ADMIN");
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(signupURL)
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		String response = mvcResult.getResponse().getContentAsString();
		
		assertEquals(status, HttpStatus.BAD_REQUEST.value());
		assertTrue(response.contains("Invalid Username"));
		
	}
	
	@Test
	public void signup_shouldReturnConflictError_whenUserExists() throws Exception {
		UserRequest user = new UserRequest("ambodji", "1234567", "ADMIN");
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(signupURL)
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		String response = mvcResult.getResponse().getContentAsString();
		
		assertEquals(status, HttpStatus.CONFLICT.value());
		assertTrue(response.contains("errors\":[\"DataIntegrityViolationException:"));
		
	}
	
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
