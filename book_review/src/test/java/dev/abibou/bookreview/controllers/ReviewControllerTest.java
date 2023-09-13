//package dev.abibou.bookreview.controllers;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import dev.abibou.bookreview.configs.Constants;
//import dev.abibou.bookreview.helpers.Converter;
//import dev.abibou.bookreview.payload.request.UserRequest;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ReviewControllerTest {
//	
//	private String jwtAdmin;
//	private String jwtSimpleUser;
//	
//	@Autowired
//	private MockMvc mockMvc;
//	
//	
//	@BeforeAll
//	public void setUp() throws Exception {
//		
//		jwtAdmin = getJWTafterMock(Constants.ADMIN_USER, Constants.LOGIN_URL);
//		jwtSimpleUser = getJWTafterMock(Constants.SIMPLE_USER, Constants.LOGIN_URL);
//		
//	}
//	
//	@Test
//	public void writeReview_ShouldWriteReview_WhenUserIsAdmin() {
//		
//		
//	}
//	
//	@Test
//	public void writeReview_ShouldWriteReview_WhenUserIsSimpleUser() {
//		
//		
//	}
//	
//	@Test
//	public void writeReview_ShouldReturnUnauthorized_WhenUserIsAdmin() {
//		
//		
//	}
//	private String getJWTafterMock(UserRequest user, String url) throws Exception {
//		
//		MvcResult mvcResult = GetMVCResultFromMockMvcPerform(user, url);
//		
//		String bodyString = mvcResult.getResponse().getContentAsString();
//		
//		return getTokenFromResponseAsString(bodyString);
//	}
//
//	private MvcResult GetMVCResultFromMockMvcPerform(UserRequest user, String url) throws Exception {
//		
//		return this.mockMvc.perform(MockMvcRequestBuilders.post(url)
//			.content(Converter.convertToString(user))
//			.contentType(MediaType.APPLICATION_JSON_VALUE))
//			.andReturn();
//	}
//
//	private String getTokenFromResponseAsString(String responseAsString) {
//		
//		return responseAsString.substring(10, responseAsString.length()-2);
//	}
//
//}
