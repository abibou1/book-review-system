package dev.abibou.bookreview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import dev.abibou.bookreview.payload.request.UserRequest;
import dev.abibou.bookreview.services.UserDetailsImpl;
import dev.abibou.bookreview.services.UserDetailsServiceImpl;

@SpringBootApplication
public class BookReviewApplication {

	public static void main(String[] args) {
		
		//SpringApplication.run(BookReviewApplication.class, args);

		ConfigurableApplicationContext context = SpringApplication.run(BookReviewApplication.class, args);

		UserDetailsServiceImpl userService = context.getBean("userDetailsServiceImpl", UserDetailsServiceImpl.class);

		UserRequest userAdmin = new UserRequest("ambodji", "1234567", "ADMIN");
		UserRequest simpleUser = new UserRequest("simpleuser", "1234567", "USER");
		
		try {
			userService.saveUser(userAdmin);
			userService.saveUser(simpleUser);
		} catch(DataIntegrityViolationException ex) {
			System.err.println("Initial users are already in the DB.");
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
