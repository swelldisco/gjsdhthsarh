package com.example.book_club.users.userExceptions;

public class UserNotFoundException extends RuntimeException  {

    public UserNotFoundException() {
        super("That user does not exist.");
    }

    public UserNotFoundException(int id) {
        super("There is no user with the id " + id + " in the repository.");
    }
    
}