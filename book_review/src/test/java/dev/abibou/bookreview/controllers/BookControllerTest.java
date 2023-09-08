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
import dev.abibou.bookreview.services.BookService;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private BookService bookService;
	
	private String saveBookURL = "/admin/book/save";
	
	
	@BeforeAll
	public void delete_bookId20() {
		bookService.deleteBookByID(20);
	}
	
	
	@Test
	public void saveBook_shouldSaveBook_whenBookInfoIsValid() throws Exception {
		Book book = new Book();
		book.setBook_id(20);
		book.setAuthor("JK Rowling");
		book.setTitle("Prisoner of Azkaban");
		book.setPublisher("Bloomsbury");
		
		MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.post(saveBookURL)
				.content(Converter.convertToString(book))
				.contentType(MediaType.APPLICATION_JSON_VALUE))
				.andReturn();
		
		int actualStatus = mvcResult.getResponse().getStatus();
		int expectedStatus = HttpStatus.CREATED.value();
		
		assertEquals(expectedStatus, actualStatus);
	}

}
