package dev.abibou.bookreview.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

import dev.abibou.bookreview.entity.Book;
import dev.abibou.bookreview.helpers.Converter;
import dev.abibou.bookreview.payload.request.UserRequest;
import dev.abibou.bookreview.services.BookService;
import dev.abibou.bookreview.configs.Constants;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private BookService bookService;
	
	private String jwtAdmin;
	private String jwtSimpleUser;
	
	@BeforeAll
	public void setUp() throws Exception {
		bookService.deleteBookByID(20);
		
		jwtAdmin = getJWTafterMockLogin(Constants.ADMIN_USER);
		jwtSimpleUser = getJWTafterMockLogin(Constants.SIMPLE_USER);
		
	}
	
	
	@Test
	public void saveBook_shouldSaveBook_whenBookInfoIsValid() throws Exception {
		Book book = new Book();
		book.setBook_id(20);
		book.setAuthor("JK Rowling");
		book.setTitle("Prisoner of Azkaban");
		book.setPublisher("Bloomsbury");
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(Constants.SAVE_BOOK_URL)
				.content(Converter.convertToString(book))
				.header("Authorization", "Bearer " + jwtAdmin)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int actualStatus = mvcResult.getResponse().getStatus();
		int expectedStatus = HttpStatus.CREATED.value();
		
		assertEquals(expectedStatus, actualStatus);
	}
	
	@Test
	public void saveBook_shouldReturnUnauthorized_whenUserIsNotAdmin() throws Exception {
		Book book = new Book();
		book.setBook_id(20);
		book.setAuthor("JK Rowling");
		book.setTitle("Prisoner of Azkaban");
		book.setPublisher("Bloomsbury");
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(Constants.SAVE_BOOK_URL)
				.content(Converter.convertToString(book))
				.header("Authorization", "Bearer " + jwtSimpleUser)
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int actualStatus = mvcResult.getResponse().getStatus();
		int expectedStatus = HttpStatus.FORBIDDEN.value();
		
		assertEquals(expectedStatus, actualStatus);
	}
	
	@Test
	public void saveBook_shouldReturnUnauthorized_whenNoTokenProvided() throws Exception {
		Book book = new Book();
		book.setBook_id(20);
		book.setAuthor("JK Rowling");
		book.setTitle("Prisoner of Azkaban");
		book.setPublisher("Bloomsbury");
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(Constants.SAVE_BOOK_URL)
				.content(Converter.convertToString(book))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int actualStatus = mvcResult.getResponse().getStatus();
		int expectedStatus = HttpStatus.UNAUTHORIZED.value();
		
		assertEquals(expectedStatus, actualStatus);
	}
	
	private String getTokenFromResponseAsString(String responseAsString) {
		
		return responseAsString.substring(10, responseAsString.length()-2);
	}
	
	private String getJWTafterMockLogin(UserRequest user) throws Exception {
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(Constants.LOGIN_URL)
				.content(Converter.convertToString(user))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		String bodyString = mvcResult.getResponse().getContentAsString();
		
		return getTokenFromResponseAsString(bodyString);
	}

}
