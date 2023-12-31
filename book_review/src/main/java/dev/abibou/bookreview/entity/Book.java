package dev.abibou.bookreview.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Book {
	@NotNull(message = "book_id is mandatory")
	@Id
	private Integer book_id;
	@NotBlank(message = "Author name is mandatory")
	private String author;
	@NotBlank(message = "Title of the book is mandatory")
	private String title;
	@NotBlank(message = "Publisher's name is mandatory")
	private String publisher;

}
