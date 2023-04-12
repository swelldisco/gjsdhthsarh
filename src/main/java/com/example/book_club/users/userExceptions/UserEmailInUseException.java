package com.example.book_club.users.userExceptions;

public class UserEmailInUseException extends RuntimeException  {

    public UserEmailInUseException(String userEmail) {
        super(userEmail + " is already in use.  Please use another email address.");
    }
    
}
