package com.vasiliska.SDJpaLibrary.repository;


import com.vasiliska.SDJpaLibrary.domain.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRep  extends CrudRepository<Genre, Long> {

    void delete(Genre genre);

    @Query("SELECT g FROM Genre g  WHERE g.genreName = :name")
    Genre getGenreByName(@Param(value = "name") String name);
}
