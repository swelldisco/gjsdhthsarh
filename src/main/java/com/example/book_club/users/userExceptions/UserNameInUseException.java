package com.example.book_club.users.userExceptions;

public class UserNameInUseException extends RuntimeException  {

    public UserNameInUseException(String userName) {
        super(userName + " is already in use.  Please pick another name.");
    }
    
}
