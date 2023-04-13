package com.example.book_club.users;

import java.util.List;
import java.util.Set;

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

import com.example.book_club.books.Book;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "User Controller", description = "Create, retrieve, update, and delete users")
@RestController
@RequestMapping(path = "/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @Operation(summary = "Retrieves a user", description = "Retrieves a user based on ID.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of a user.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @Operation(summary = "Retrieves all users.", description = "Retrieves all users currently in the repository.")
    @ApiResponse(responseCode = "200", description = "Successful retrieval of all users.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @GetMapping("/all") 
    public ResponseEntity<List<User>> getUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @Operation(summary = "Creates a user.", description = "Saves a user to the repository based on a validated user object with unique userName and userEmail.")
    @ApiResponse(responseCode = "201", description = "Successful creation of a user.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @PostMapping
        public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
            return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @Operation(summary = "Updates a user.", description = "Updates a user based on ID and validated user object.")
    @ApiResponse(responseCode = "202", description = "Successful update of a user.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@Valid User user, @PathVariable int id) {
        return new ResponseEntity<>(userService.updateUser(user, id), HttpStatus.ACCEPTED);
    }

    @Operation(summary = "Deletes a user.", description = "Deletes a user based on ID.")
    @ApiResponse(responseCode = "204", description = "Successful deletion of a user.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Adds a book to a user's libray.", description = "Using user and book ID, this adds a book object already in the repository to a user's person library set, and returns the updated set.")
    @ApiResponse(responseCode = "200", description = "Successfully added a book to a user's library.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @PutMapping("/{userId}/addBook/{bookId}")
    public ResponseEntity<Set<Book>> addBookToLibrary(@PathVariable int userId, @PathVariable int bookId) {
        return new ResponseEntity<>(userService.addBookToLibray(userId, bookId), HttpStatus.OK);
    }

    @Operation(summary = "Removes a book from a user's library.", description = "Using user and book ID, checks a user's library set to see if the book is in it.  If it is, it removes the book and returns the updated set.")
    @ApiResponse(responseCode = "200", description = "Successful removal of a book from a user's library.", content = @Content(array = @ArraySchema(schema = @Schema(implementation = User.class))))
    @PutMapping("/{userId}/removeBook/{bookId}")
    public ResponseEntity<Set<Book>> removeBookFromLibrary(@PathVariable int userId, @PathVariable int bookId) {
        return new ResponseEntity<>(userService.removeBookFromLibrary(userId, bookId), HttpStatus.OK);
    }
}
