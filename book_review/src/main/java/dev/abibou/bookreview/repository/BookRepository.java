package dev.abibou.bookreview.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.abibou.bookreview.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

}
