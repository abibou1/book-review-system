package dev.abibou.bookreview.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.abibou.bookreview.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
	
	List<Review> findByBookId(Integer book_id);
}
