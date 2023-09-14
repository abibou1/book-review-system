package dev.abibou.bookreview;

import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import dev.abibou.bookreview.configs.Constants;
import dev.abibou.bookreview.entity.Review;
import dev.abibou.bookreview.services.ReviewService;
import dev.abibou.bookreview.services.UserDetailsServiceImpl;

@SpringBootApplication
public class BookReviewApplication {

	public static void main(String[] args) {
		
		//SpringApplication.run(BookReviewApplication.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(BookReviewApplication.class, args);

		UserDetailsServiceImpl userService = context.getBean("userDetailsServiceImpl", UserDetailsServiceImpl.class);
		ReviewService reviewService = context.getBean("reviewService", ReviewService.class);

		userService.saveUser(Constants.ADMIN_USER);
		userService.saveUser(Constants.SIMPLE_USER);
		
		
		
		Random random = new Random();
		for(int i=0; i<10;i++) {
			
			Review review = new Review();
			review.setUsername(Constants.SIMPLE_USER.getUsername());
			review.setComment("This is a great book.");
			
			review.setBookId(random.nextInt(6));
			
			reviewService.saveReview(review);
		}
		
		List<Review> reviews = reviewService.getReviewsByBook_id(3);
		for(Review review: reviews){
			System.err.println(review);
		}
		
		
//		System.out.println(userService.saveUser(userInfo));
		
//		String username = "john";
//		
//		System.out.println("deleted: "+userService.deleteUser(username));



		// System.out.println(userService.deleteUser("rong"));
//
//		userService.deleteUser("rong");

		// System.out.println(userService.getUserByUsername("hermi"));


//		ConfigurableApplicationContext context = SpringApplication.run(BookReviewApplication.class, args);
//
//		BookService bookService = context.getBean("bookService", BookService.class);
//
//
//		//BookRepository bookRepo = context.getBean("bookRepository", BookRepository.class);
//
//		Book book = new Book();
//
//		book.setId(1);
//		book.setAuthor("Steven King");
//		book.setTitle("The Outsider");
//		book.setPublisher("Scribner");
////
////		bookRepo.save(book);
////
////		Optional<Book> savedBook = bookRepo.findById(1);
////
////		System.out.println("-------Saved Book--------");
////
////		System.out.println(savedBook.toString());
//
//
//		bookService.saveBook(book);
//		Book oneBook = bookService.getBookByID(2);
//
//		System.out.println(oneBook);


	}

}
