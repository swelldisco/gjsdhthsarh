package com.example.book_club.users;

import java.util.List;
import java.util.Set;

import com.example.book_club.books.Book;

public interface UserService {
    User getUser(int id);
    User createUser(User user);
    User updateUser(User user, int id);
    void deleteUser(int id);
    List<User> getAllUsers();
    Set<Book> getLibrary(int id);
    Set<Book> addBookToLibray(int userId, int bookId);
    Set<Book> removeBookFromLibrary(int userId, int bookId);
    
}
