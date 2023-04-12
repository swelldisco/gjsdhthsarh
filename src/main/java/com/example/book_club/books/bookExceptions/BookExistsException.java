package com.example.book_club.books.bookExceptions;

import com.example.book_club.books.Book;

public class BookExistsException extends RuntimeException { 

    public BookExistsException(Book book) {
        super("\"" + book.getTitle() + "\" by " + book.getAuthor() + ", published in "+ book.getPublicationDate() + " is already in our repository");
    }
    
}
