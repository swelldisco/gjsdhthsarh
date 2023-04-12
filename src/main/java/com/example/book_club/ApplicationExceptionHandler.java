package com.example.book_club;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.book_club.books.bookExceptions.AuthorNotFoundException;
import com.example.book_club.books.bookExceptions.BookExistsException;
import com.example.book_club.books.bookExceptions.BookNotFoundException;
import com.example.book_club.books.bookExceptions.EmptyRepositoryException;
import com.example.book_club.exceptions.ErrorResponse;
import com.example.book_club.users.userExceptions.UserException;

// from Learn the Part's Spring-Boot course https://www.learnthepart.com/
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler({AuthorNotFoundException.class, BookExistsException.class, BookNotFoundException.class, EmptyRepositoryException.class, UserException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList(ex.getMessage()));  
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // this doesn't seem to apply to Crudrepository when using deleteById
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleDataAccessException(EmptyResultDataAccessException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Resource does not exist."));  
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ErrorResponse error = new ErrorResponse(Arrays.asList("Data Integrity Violation: we cannot process your request."));  
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
  
    @Override 
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
      List<String> errors = new ArrayList<>();
      ex.getBindingResult().getAllErrors().forEach((error) -> errors.add(error.getDefaultMessage()));
      return new ResponseEntity<>(new ErrorResponse(errors), HttpStatus.BAD_REQUEST);
    }    
}
