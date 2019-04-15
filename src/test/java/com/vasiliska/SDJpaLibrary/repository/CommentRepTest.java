package com.vasiliska.SDJpaLibrary.repository;

import com.vasiliska.SDJpaLibrary.domain.Author;
import com.vasiliska.SDJpaLibrary.domain.Book;
import com.vasiliska.SDJpaLibrary.domain.Comment;
import com.vasiliska.SDJpaLibrary.domain.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@DataJpaTest
public class CommentRepTest {

    @Autowired
    private BookRep bookRep;

    @Autowired
    private CommentRep commentRep;

    @Autowired
    private AuthorRep authorRep;

    @Autowired
    private GenreRep genreRep;

    private final String TEST_BOOK_NAME1 = "Му-му";
    private final String TEST_COMMENT = "Жуть, читал и плакал, советую!";
    private final String TEST_AUTHOR = "Тургенев";
    private final String TEST_GENRE = "Драма";

    @Test
    public void getCommentByBookTest() {
        Book book = insertBook();
        commentRep.save(new Comment(TEST_COMMENT, book));
        long idBook = book.getBookId();
        Comment comment = commentRep.getCommentByBook(idBook).get(0);
        assertTrue(comment.getCommentText().equals(TEST_COMMENT));
    }


    public Book insertBook() {
        Author author = new Author(TEST_AUTHOR);
        authorRep.save(author);

        Genre genre = new Genre(TEST_GENRE);
        genreRep.save(genre);

        Book book = new Book(TEST_BOOK_NAME1, author, genre);
        bookRep.save(book);
        return book;
    }
}