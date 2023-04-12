package com.example.book_club.books;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import com.example.book_club.books.bookExceptions.AuthorNotFoundException;
import com.example.book_club.books.bookExceptions.BookException;
import com.example.book_club.books.bookExceptions.BookExistsException;
import com.example.book_club.books.bookExceptions.BookNotFoundException;
import com.example.book_club.books.bookExceptions.EmptyRepositoryException;
import com.example.book_club.constants.ErrorMessages;

@Service
public class BookServiceImpl implements BookService {
    
    @Autowired
    BookRepository bookRepository;
    
    @Override
    public Book getBook(int id) {
        Optional<Book> optionalBook = bookRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new BookNotFoundException(id);
        }
    };

    @Override
    public Book createBook(Book book) {
        if (!bookExists(book)) {
            return bookRepository.save(book);
        } else {
            throw new BookExistsException(book);
        }
    };

    public Book updateBook(Book updatedBook, int id) {
        if (bookRepository.existsById(id)) {
            Book book = new Book(updatedBook);
            book.setBookId(id);
            return bookRepository.save(book);
        } else {
            throw new BookNotFoundException(id);
        }
    };

    @Override
    public void deleteBook(int id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
        } else {
            throw new BookNotFoundException(id);
        }
    };

    @Override
    public List<Book> getAllBooks() {
        List<Book> bookList = (List<Book>)bookRepository.findAll();
        if(bookList != null && !bookList.isEmpty()) {
            return bookList;
        } else {
            throw new EmptyRepositoryException();
        }
    }

    @Override
    public List<Book> searchBooks(String target) {
        List<Book> bookList = bookRepository.findByTitleOrAuthor(target);
        if(bookList != null && !bookList.isEmpty()) {
            return bookList;
        } else {
            throw new BookNotFoundException(target);
        }
    }

    public List<Book> searchByAuthor(String author) {
        List<Book> bookList = bookRepository.findByAuthorIgnoreCase(author);
        if(bookList != null && !bookList.isEmpty()) {
            return bookList;
        } else {
            //throw new AuthorNotFoundException(author);
            throw new BookException(ErrorMessages.authorNotFound1 + author + ErrorMessages.authorNotFound2);
        }
    }

    public List<Book> searchByTitle(String title) {
        List<Book> bookList = bookRepository.findByTitleIgnoreCase(title);
        if(bookList != null && !bookList.isEmpty()) {
            return bookList;
        } else {
            throw new BookException(title + ErrorMessages.titleNotFound);
        }
    }

    public List<Book> searchByyear(int year) {
        List<Book> bookList = bookRepository.findByPublicationDate(year);
        if(bookList != null && !bookList.isEmpty()) {
            return bookList;
        } else {
            throw new BookNotFoundException();
        }
    }

    public int getIdByTitleAndAuthor(String title, String author) {
        List<Book> bookList = bookRepository.findByTitleAndAuthorIgnoreCase(title, author);
        if(bookList != null && !bookList.isEmpty()) {
            int id = bookList.stream()
                .map(b -> b.getBookId())
                .findFirst()
                .orElseThrow(() -> new BookNotFoundException(title, author));
            return id;
        } else {
            throw new BookNotFoundException(title, author);
        }
    }

    public static Book pokeOptionalBook(Optional<Book> optionalBook) {
        if (optionalBook.isPresent()) { 
            return optionalBook.get();
        } else {
            throw new BookNotFoundException();
        }    
    }

    // not sure I need this anymore, but it can hang around for a bit
    public static List<Book> pokeOptionalList(Optional<List<Book>> optionalList) {
        if (optionalList.isPresent()) {
            List<Book> bookList = optionalList.get();
            if(bookList != null && !bookList.isEmpty()) {
                return bookList;
            } else {
                throw new BookNotFoundException();
            }
        } else {
            throw new BookNotFoundException();
        }
    }

    // this is ugly, and I am sorry.
    public boolean bookExists(Book book) {
        return (bookRepository.existsByAuthorAndTitleIgnoreCase(book.getAuthor(), book.getTitle()) && bookRepository.existsByPublicationDate(book.getPublicationDate()));
    }

}
