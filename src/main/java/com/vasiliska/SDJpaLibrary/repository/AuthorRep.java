package com.vasiliska.SDJpaLibrary.repository;


import com.vasiliska.SDJpaLibrary.domain.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRep extends CrudRepository<Author, Long> {

    void delete(Author author);

    @Query("SELECT a FROM Author a  WHERE a.authorName = :name")
    Author getAuthorByName(@Param(value = "name") String name);
}
