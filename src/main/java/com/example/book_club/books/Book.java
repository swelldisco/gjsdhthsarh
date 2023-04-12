package com.example.book_club.books;

import java.util.Set;

import com.example.book_club.users.User;
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

//import com.example.book_club.constants.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@SequenceGenerator(name = "book_id_generator", sequenceName = "book_sequence", allocationSize = 1)
    @Column(name = "book_id")
    private int bookId;
    
    @NotBlank(message = "Author cannot be left blank")
    @Column(name = "author", nullable = false)
    @NonNull
    private String author;

    @NotBlank(message = "Title cannot be left blank")
    @Column(name = "title", nullable = false)
    @NonNull
    private String title;

    @NotNull(message = "Publication date cannot be left blank")
    @Column(name = "publication_date", nullable = false)
    private int publicationDate;

    @JsonIgnore
    // @ManyToMany(mappedBy = "bookLibrary")
    @ManyToMany
    @JoinTable(name = "user_library",
    joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "book_id"),
    inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"))
    private Set<User> userLibrary;

    // might use later
    //private Genre genre;

    public Book(String author, String title, int publicationDate) {
        this.author = author;
        this.title = title;
        this.publicationDate = publicationDate;
    };

    public Book(int bookId, String author, String title, int publicationDate) {
        this.bookId = bookId;
        this.author = author;
        this.title = title;
        this.publicationDate = publicationDate;
    }

    public Book(Book source) {
        this.bookId = source.bookId;
        this.author = source.author;
        this.title = source.title;
        this.publicationDate = source.publicationDate;
        this.userLibrary = source.userLibrary;
    }

    @Override
    public String toString() {
        return " \"" + getTitle() + "\", by " + getAuthor() + ", published in " + getPublicationDate();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((author == null) ? 0 : author.hashCode());
        result = prime * result + ((title == null) ? 0 : title.hashCode());
        result = prime * result + publicationDate;
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
        Book other = (Book) obj;
        if (author == null) {
            if (other.author != null)
                return false;
        } else if (!author.equals(other.author))
            return false;
        if (title == null) {
            if (other.title != null)
                return false;
        } else if (!title.equals(other.title))
            return false;
        if (publicationDate != other.publicationDate)
            return false;
        return true;
    }
    
}
