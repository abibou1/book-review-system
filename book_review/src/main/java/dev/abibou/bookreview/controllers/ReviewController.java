package dev.abibou.bookreview.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {
	@PostMapping("authenticated/review/write/{book_id}")
	public ResponseEntity<String> writeReview(@PathVariable("book_id") int bookId){ //, @RequestBody String comment){
		
		return new ResponseEntity<>("Book ID:" + bookId + ", thank you for your feedback", HttpStatus.OK);
	}

}
