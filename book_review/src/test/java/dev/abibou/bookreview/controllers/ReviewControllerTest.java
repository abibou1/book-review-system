package dev.abibou.bookreview.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

import dev.abibou.bookreview.configs.Constants;
import dev.abibou.bookreview.entity.Review;
import dev.abibou.bookreview.helpers.Converter;
import dev.abibou.bookreview.payload.request.ReviewRequest;
import dev.abibou.bookreview.payload.request.UserRequest;
import dev.abibou.bookreview.services.ReviewService;
import dev.abibou.bookreview.utils.JwtUtil;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ReviewControllerTest {
	
	private String jwtAdmin;
	private String jwtSimpleUser;
	
	private String urlWriteReview = "/authenticated/review/write/";
	private String urlGetReviewByBookId = "/authenticated/review/get-reviews-of/{book_id}";
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	ReviewService reviewService;

	@Autowired
	JwtUtil jwtUtil;
	
	
	@BeforeAll
	public void setUp() throws Exception {
		
		jwtAdmin = getJWTafterMock(Constants.ADMIN_USER, Constants.LOGIN_URL);
		jwtSimpleUser = getJWTafterMock(Constants.SIMPLE_USER, Constants.LOGIN_URL);
		
	}
	
	@Test
	public void writeReview_ShouldWriteReview_WhenUserIsAdmin() throws Exception {
		String username = jwtUtil.getUserNameFromJwtToken(jwtAdmin);
		int book_id = 100;
		String comment = "This is an amazing book";
		
		Review review = new Review();
		review.setUsername(username);
		review.setBookId(book_id);
		review.setComment(comment);
		
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(urlWriteReview+book_id)
				.content(Converter.convertToString(new ReviewRequest(comment)))
				.header("Authorization", "Bearer " + jwtAdmin)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		String bodyString = mvcResult.getResponse().getContentAsString();
		
		int actualStatus = mvcResult.getResponse().getStatus();
		int expectedStatus = HttpStatus.CREATED.value();
		
		System.err.println("Body" + bodyString);
		
		assertEquals(expectedStatus, actualStatus);
		assertTrue(bodyString.contains("review_id")
				&& bodyString.contains("username")
				&& bodyString.contains("bookId")
				&& bodyString.contains("comment"));
		
		
		
	}
	
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
	private String getJWTafterMock(UserRequest user, String url) throws Exception {
		
		MvcResult mvcResult = GetMVCResultFromMockMvcPerform(user, url);
		
		String bodyString = mvcResult.getResponse().getContentAsString();
		
		return getTokenFromResponseAsString(bodyString);
	}

	private MvcResult GetMVCResultFromMockMvcPerform(UserRequest user, String url) throws Exception {
		
		return this.mockMvc.perform(MockMvcRequestBuilders.post(url)
			.content(Converter.convertToString(user))
			.contentType(MediaType.APPLICATION_JSON_VALUE))
			.andReturn();
	}

	private String getTokenFromResponseAsString(String responseAsString) {
		
		return responseAsString.substring(10, responseAsString.length()-2);
	}

}
