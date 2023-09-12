package dev.abibou.bookreview.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import dev.abibou.bookreview.entity.Book;
import dev.abibou.bookreview.services.BookService;
import jakarta.validation.Valid;

@RestController
public class BookController {
	@Autowired
	BookService bookService;
	
	@PostMapping("/admin/book/hello")
	public String sayHello() {
		return "Hello there!!!";
	}

	@PostMapping("/admin/book/save")
	public ResponseEntity<String> saveBook(@Valid @RequestBody Book book) {

		bookService.saveBook(book);

		String response = "book with id="+book.getBook_id()+ " is saved.";
		return new ResponseEntity<String>(response, HttpStatus.CREATED);

	}

	@GetMapping("/authenticated/book/all-books")
	public ResponseEntity<List<Book>> getAllBooks(){
		List<Book> books = bookService.getAllBooks();

		return new ResponseEntity<>(books, HttpStatus.OK);
	}
}
