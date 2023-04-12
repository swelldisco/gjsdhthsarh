package com.example.book_club.books.bookExceptions;

public class BookNotFoundException extends RuntimeException { 

    public BookNotFoundException() {
        super("This book does not exist in our repository");
    }

    public BookNotFoundException(int id) {
        super("A book with ID " + id + " does not exist in our repository");
    }

    public BookNotFoundException(String title) {
        super("\"" + title + "\" does not exist in our repository");
    }

    public BookNotFoundException(String title, String author) {
        super("\"" + title + "\" by " + author + "does not exist in our repository");
    }
    
}
