package com.vasiliska.SDJpaLibrary.domain;


import lombok.Data;

import javax.persistence.*;


@Data
@Entity(name = "Comment")
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @Column(name = "comment_text")
    private String commentText;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    public Comment(String commentText, Book book) {
        this.commentText = commentText;
        this.book = book;
    }

    public Comment() {
    }

    @Override
    public String toString() {
        return book.getBookName() + ": " + commentText+"\n";
    }


}
