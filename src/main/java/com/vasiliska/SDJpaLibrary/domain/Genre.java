package com.vasiliska.SDJpaLibrary.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "Genre")
@Table(name = "genres")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "genre_id")
    private Long genreId;

    @Column(name = "genre_name")
    private String genreName;

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre() {
    }
}
