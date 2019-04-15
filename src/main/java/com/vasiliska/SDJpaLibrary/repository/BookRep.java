package com.vasiliska.SDJpaLibrary.repository;


import com.vasiliska.SDJpaLibrary.domain.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRep extends CrudRepository<Book, Long> {

    List<Book> findAll();

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.author a WHERE a.authorName = :authorName")
    List<Book> getBookByAuthor(@Param(value = "authorName") String authorName);

    @Query("SELECT b FROM Book b LEFT JOIN FETCH b.genre g WHERE g.genreName = :genreName")
    List<Book> getBookByGenre(@Param(value = "genreName") String genreName);

    @Query("SELECT b FROM Book b WHERE b.bookName =:name")
    List<Book> getBookByName(@Param(value = "name") String name);

    void delete(Book book);
}
