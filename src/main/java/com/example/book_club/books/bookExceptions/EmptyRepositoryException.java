package com.example.book_club.books.bookExceptions;

public class EmptyRepositoryException extends RuntimeException { 

    public EmptyRepositoryException() {
        super("There are no books in the repository.");
    }
    
}
