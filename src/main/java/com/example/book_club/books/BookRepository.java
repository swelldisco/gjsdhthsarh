package com.example.book_club.books;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Integer> {
    
    // Lists return empty when nothing is found, so test for that in the service layer

    // a lot of these are for future functionality like getting a list of all books by a single author or published in a given year.
    List<Book> findByAuthorIgnoreCase(String author);
    List<Book> findByTitleIgnoreCase(String title);
    List<Book> findByPublicationDate(int publicationDate);
    List<Book> findByTitleAndAuthorIgnoreCase(String title, String author);
    boolean existsByAuthorIgnoreCase(String author);
    boolean existsByTitleIgnoreCase(String title);
    boolean existsByPublicationDate(int publicationDate);
    boolean existsByAuthorAndTitleIgnoreCase(String author, String title);

    @Query("select b from Book b where UPPER(b.author) = UPPER(?1) or UPPER(b.title) = UPPER(?1)")
    List<Book> findByTitleOrAuthor(String target);

}
