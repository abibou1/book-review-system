package dev.abibou.bookreview.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.abibou.bookreview.entity.Review;
import dev.abibou.bookreview.helpers.Converter;
import dev.abibou.bookreview.services.ReviewService;
import dev.abibou.bookreview.utils.JwtUtil;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/authenticated/review")
public class ReviewController {
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	JwtUtil jwtUtil;
	
	@PostMapping("/write/{book_id}")
	public ResponseEntity<?> writeReview(@PathVariable ("book_id") int bookId,
			@RequestHeader("Authorization") String headerToken,
			@RequestBody String comment){
		
		String token = Converter.getJWtTokenFromHeader(headerToken);
		String username = jwtUtil.getUserNameFromJwtToken(token);
		
		Review review = new Review();
		review.setUsername(username);
		review.setBookId(bookId);
		review.setComment(comment);
		
		reviewService.saveReview(review);
		
		
		
		return new ResponseEntity<>(review, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/get-reviews-of/{book_id}")
	public ResponseEntity<?> getReviewsOf(@PathVariable ("book_id") int bookId){
		List<Review> reviewsOf = reviewService.getReviewsByBook_id(bookId);
		
		return new ResponseEntity<>(reviewsOf, HttpStatus.OK);
	}

}
