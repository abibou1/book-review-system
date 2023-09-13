package dev.abibou.bookreview.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ReviewController {
	@PostMapping("/authenticated/review/write{book_id}")
public ResponseEntity<String> writeReview(@RequestParam ("book_id") int bookId, @RequestBody String comment){
		
		return new ResponseEntity<>("Book ID:" + bookId + ", thank you for your feedback", HttpStatus.OK);
	}

}
