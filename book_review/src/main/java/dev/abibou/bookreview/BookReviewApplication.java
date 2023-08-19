package dev.abibou.bookreview;

import java.util.Optional;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import dev.abibou.bookreview.entity.Book;
import dev.abibou.bookreview.repository.BookRepository;
import dev.abibou.bookreview.services.BookService;

@SpringBootApplication
public class BookReviewApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookReviewApplication.class, args);
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
