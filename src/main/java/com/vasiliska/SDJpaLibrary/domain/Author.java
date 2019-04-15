package com.vasiliska.SDJpaLibrary.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Author")
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private Long authorId;

    @Column(name = "author_name")
    private String authorName;

    public Author() {
           }
    
    public Author(String authorName) {
        this.authorName = authorName;
       }
}
