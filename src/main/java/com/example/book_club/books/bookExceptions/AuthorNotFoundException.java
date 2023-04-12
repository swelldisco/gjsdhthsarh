package com.example.book_club.books.bookExceptions;

public class AuthorNotFoundException extends RuntimeException  {

    public AuthorNotFoundException(String author) {
        super("No books by " + author + " exist in our repository");
    }
    
}
