package dev.abibou.bookreview.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.abibou.bookreview.entity.Review;
import dev.abibou.bookreview.repository.ReviewRepository;

@Service
public class ReviewService {
	@Autowired
	ReviewRepository reviewRepo;
	
	public boolean saveReview(Review review) {
		
		try {
			reviewRepo.save(review);
		}
		catch(IllegalArgumentException ex) {
			throw new IllegalArgumentException("Review entity must not be null. "+ex.getMessage());
		}
		
		return true;
	}
	
	public List<Review> getReviewsByBook_id(int book_id){
		
		return reviewRepo.findByBookId(book_id);
	}
	

}
