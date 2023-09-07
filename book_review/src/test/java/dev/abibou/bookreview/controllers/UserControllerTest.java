package dev.abibou.bookreview.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
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

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	private String signupURL = "/api/auth/signup";
	private String loginURL="/api/auth/login";
	
	/*
	 * 
	 * testing signup:dkd
	 * empty username
	 * short pwd =>
	 * existing user
	 * 
	 */
	
	@Test
	public void signup_shouldRegisterAdmin_whenDataIsValid() throws Exception {
		
		UserRequest user = new UserRequest("jonhD", "1234", "ADMIN");
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(signupURL)
				.content(asJsonString(user))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		
		assertEquals(status, HttpStatus.CREATED);

	}
	
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}

}
