package dev.abibou.bookreview.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="book")// optional as table and entity have the exact name
@RequiredArgsConstructor
@AllArgsConstructor
@Data
public class Book {
	@Id
	@Column(name="book_id")
	private Integer id;
	@Column
	private String author;
	@Column
	private String title;
	@Column
	private String publisher;

}
