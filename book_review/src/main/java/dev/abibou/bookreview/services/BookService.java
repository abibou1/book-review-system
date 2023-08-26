package dev.abibou.bookreview.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.abibou.bookreview.entity.Book;
import dev.abibou.bookreview.repository.BookRepository;

@Service
public class BookService {
	@Autowired
	BookRepository bookRepository;

	public void saveBook(Book book) {
		bookRepository.save(book);
	}

	public Book getBookByID(int id) {

		Optional<Book> book = bookRepository.findById(id);
		return book.isPresent()?book.get():new Book();
	}

	public List<Book> getAllBooks(){
		return bookRepository.findAll();
	}

}
