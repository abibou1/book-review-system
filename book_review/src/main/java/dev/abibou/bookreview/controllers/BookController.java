package dev.abibou.bookreview.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.abibou.bookreview.entity.Book;
import dev.abibou.bookreview.services.BookService;

@RestController
@RequestMapping("/book")
public class BookController {
	@Autowired
	BookService bookService;
	
	@PostMapping("/save")
	public ResponseEntity<String> saveBook(@RequestBody Book book) {
		
		bookService.saveBook(book);
		
		String response = "book with id="+book.getId()+ " is saved.";
		return new ResponseEntity<String>(response, HttpStatus.CREATED);
		
	}
	

}
