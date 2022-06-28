package com.example.demo.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;

@Entity(
        name = "Book"
)
@Table(
        name="book"
)
@Data
@AllArgsConstructor
public class Book {
    @Id
    @SequenceGenerator(
            name="book_sequence",
            sequenceName = "book_sequence",
            allocationSize = 1
    )
    @GeneratedValue(generator = "book_sequence")
    private Long id;

    @Column(name = "title")
    @NotNull
    @NotEmpty
    @Size( min=2, message = "title should have more than 2 characters")
    private String title;

    @Column(name = "author")
    @NotNull
    @Size(
            min=2,
            message = "author should have more than 2 characters"
    )
    @NotEmpty
    private String author;

    @Column(name = "genre")
    @NotNull
    @NotEmpty
    @Size( min=2, message = "genre should have more than 2 characters")
    private String genre;

    @Column(name = "year")
    @NotNull()
    private int year;

    public Book(){}

    public Book(String title, String author, String genre, int year) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
    }

    @Override
    public boolean equals(Object o) {
        Book book = (Book) o;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
