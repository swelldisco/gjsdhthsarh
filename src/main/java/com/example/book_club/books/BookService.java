package com.example.book_club.books;

import java.util.List;

public interface BookService {
    Book getBook(int id);
    Book createBook(Book book);
    Book updateBook(Book book, int id);
    void deleteBook(int id);
    List<Book> getAllBooks();
    List<Book> searchBooks(String target);

}
