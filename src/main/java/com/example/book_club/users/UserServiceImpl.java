package com.example.book_club.users;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.book_club.books.Book;
import com.example.book_club.books.BookRepository;
import com.example.book_club.books.BookServiceImpl;
import com.example.book_club.constants.ErrorMessages;
import com.example.book_club.users.userExceptions.UserException;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;
    
    @Override
    public User getUser(int id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return pokeOptionalUser(optionalUser);
    }

    @Override
    public User createUser(User user) {
        if (userRepository.existsByUserNameAndUserEmailIgnoreCase(user.getUserName(), user.getUserEmail())) {
            throw new UserException(ErrorMessages.userNameAndEmailInUser);
        } else if (userRepository.existsByUserNameIgnoreCase(user.getUserName())) {
            throw new UserException(user.getUserName() + ErrorMessages.userNameInUse);
        } else if (userRepository.existsByUserEmailIgnoreCase(user.getUserEmail())) {
            throw new UserException(user.getUserEmail() + ErrorMessages.userEmailInUse);
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public User updateUser(User tempUser, int id) {
        if (userRepository.existsById(id)) {
            User user = new User(tempUser);
            user.setUserId(id);
            return userRepository.save(user);
        } else {
            throw new UserException(ErrorMessages.userNotFound + "Id: " + id);
        }
    }

    @Override
    public void deleteUser(int id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new UserException(ErrorMessages.userNotFound + "Id: " + id);
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> userList = (List<User>)userRepository.findAll();
        if (userList != null && !userList.isEmpty()) {
            return userList;
        } else {
            throw new UserException(ErrorMessages.emptyRepo);
        }
    }

    @Override
    public Set<Book> getLibrary(int id) {
        return getUser(id).getBookLibrary();
    }

    public Set<Book> addBookToLibray(int userId, int bookId) {
        // find out why I can't access book methods through the bidirection many to many relationship.
        // also, does it matter?
        // doesn't matter, but it's weird and I should find out what's going on.
        User user = getUser(userId);
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = BookServiceImpl.pokeOptionalBook(optionalBook);
        Set<Book> bookSet = getLibrary(userId);
        bookSet.add(book);
        userRepository.save(user);
        return bookSet;
    }

    public Set<Book> removeBookFromLibrary(int userId, int bookId) {
        User user = getUser(userId);
        Optional<Book> optionalBook = bookRepository.findById(bookId);
        Book book = BookServiceImpl.pokeOptionalBook(optionalBook);
        Set<Book> bookSet = getLibrary(userId);
        if (bookSet.contains(book)) {
            System.out.println("true!");
            bookSet.remove(book);
            userRepository.save(user);
            return bookSet;
        } else {
            System.out.println("no can do");
            throw new UserException("\"" + book.getTitle() + "\" " +ErrorMessages.deleteBookFromLibraryFailed);
        }
    }

    public int findIdByUserName(String userName) {
        Optional<User> optionalUser = userRepository.findByUserName(userName);
        return pokeOptionalUser(optionalUser).getUserId();
    }

    public static User pokeOptionalUser(Optional<User> optionalUser) {
        if (optionalUser.isPresent()) { 
            return optionalUser.get();
        } else {
            throw new UserException(ErrorMessages.userNotFound);
        }    
    }
    // need ways to add and remove from lists


}
