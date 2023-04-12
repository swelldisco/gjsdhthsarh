package com.example.book_club.books;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Book Controller", description = "Create, retrieve, update, and delete books")
@RestController
@RequestMapping(path = "/books")
public class BookController {
    
    @Autowired
    BookService bookService;

    @Operation(summary = "Retrieves a book", description = "Retrieves a book based on ID")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of a book", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        Book book = bookService.getBook(id);
        return new ResponseEntity<Book>(book, HttpStatus.OK);
    }

    @Operation(summary = "Retrieves all books", description = "Returns a list of all books in the repository")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of a list of books", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @Operation(summary = "Creates a book", description = "Creates a new book based on provided input")
    @ApiResponse(responseCode = "201", description = "Successful creation of a new book", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))
    @PostMapping 
    public ResponseEntity<Book> createBook(@Valid @RequestBody Book book) {
        return new ResponseEntity<>(bookService.createBook(book), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a book", description = "Allows a book's information to be updated based on ID")
    @ApiResponse(responseCode = "202", description = "Successfully updated a preexisting book", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book, @PathVariable int id) {
        return new ResponseEntity<>(bookService.updateBook(book, id), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Deletes a book", description = "Removes a book from the repository based on ID")
    @ApiResponse(responseCode = "204", description = "Successful deletion of a book", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteBook(@PathVariable int id) {
        bookService.deleteBook(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    // I have no idea how to implement this yet, so this is a place holder which only works by taking one string, either title or author, and searing the database for it and returning a list of matching books.  Chances are I'll need to do some kind of frontend work to understand the sorts of queries and forms that go between front and back end, then revisit this to make it more funtional.
    @Operation(summary = "Search for books", description = "Returns a list of books in the repository based on search queries.  NYI/Nowhere near implemented!")
    @ApiResponse(responseCode = "200", description = "Successfully returned a list of books", content = @Content(array = @ArraySchema(schema = @Schema(implementation = Book.class))))
    @GetMapping("/search/{target}")
    public ResponseEntity<List<Book>> searchBooks(@PathVariable String target) {
        List<Book> books = bookService.searchBooks(target);
        return new ResponseEntity<>(books, HttpStatus.OK);
    }
}
