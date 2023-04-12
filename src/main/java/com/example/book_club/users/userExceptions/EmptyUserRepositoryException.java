package com.example.book_club.users.userExceptions;

public class EmptyUserRepositoryException extends RuntimeException  {

    public EmptyUserRepositoryException() {
        super("There are no users in the repository.");
    }
    
}
