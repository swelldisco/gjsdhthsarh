package com.example.book_club.users;

import java.util.Set;

import com.example.book_club.books.Book;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @NotBlank(message = "Your username cannot be blank.")
    @NonNull
    @Column(name = "user_name", nullable = false)
    private String userName;

    @NotBlank(message = "You email cannt be left blank.")
    @Email(message = "Please enter a valid email address.")
    @Column(name = "user_email", nullable = false)
    private String userEmail;

    public User(String userName, String userEmail) {
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public User(int userId, String userName, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    public User(User source) {
        this.userId = source.userId;
        this.userName = source.userName;
        this.userEmail = source.userEmail;
        this.bookLibrary = source.bookLibrary;
    }

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "user_library",
    joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id", referencedColumnName = "booK_id"))
    private Set<Book> bookLibrary;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userId;
        result = prime * result + ((userName == null) ? 0 : userName.hashCode());
        result = prime * result + ((userEmail == null) ? 0 : userEmail.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (userId != other.userId)
            return false;
        if (userName == null) {
            if (other.userName != null)
                return false;
        } else if (!userName.equals(other.userName))
            return false;
        if (userEmail == null) {
            if (other.userEmail != null)
                return false;
        } else if (!userEmail.equals(other.userEmail))
            return false;
        return true;
    }

}
