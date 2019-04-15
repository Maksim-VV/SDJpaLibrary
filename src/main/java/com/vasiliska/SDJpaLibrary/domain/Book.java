package com.vasiliska.SDJpaLibrary.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Book")
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "name")
    private String bookName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre_id")
    private Genre genre;

    public Book(String bookName, Author author, Genre genre) {
        this.bookName = bookName;
        this.author = author;
        this.genre = genre;
    }

    public Book() {
    }

    @Override
    public String toString() {
        return bookName + " " + author.getAuthorName() + " " + genre.getGenreName()+"\n";
    }

}
