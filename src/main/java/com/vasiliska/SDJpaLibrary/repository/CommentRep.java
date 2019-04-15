package com.vasiliska.SDJpaLibrary.repository;


import com.vasiliska.SDJpaLibrary.domain.Book;
import com.vasiliska.SDJpaLibrary.domain.Comment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRep extends CrudRepository<Comment, Long> {

    @Query("SELECT d FROM Comment d WHERE d.book.bookId = :bookId")
    List<Comment> getCommentByBook(@Param(value = "bookId") Long bookId);

}
