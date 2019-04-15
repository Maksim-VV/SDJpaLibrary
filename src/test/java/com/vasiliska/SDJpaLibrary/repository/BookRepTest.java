package com.vasiliska.SDJpaLibrary.repository;

import com.vasiliska.SDJpaLibrary.domain.Author;
import com.vasiliska.SDJpaLibrary.domain.Book;
import com.vasiliska.SDJpaLibrary.domain.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@DataJpaTest
public class BookRepTest {

    @Autowired
    private BookRep bookRep;

    @Autowired
    private AuthorRep authorRep;

    @Autowired
    private GenreRep genreRep;

    private final String TEST_BOOK_NAME1 = "Му-му";
    private final String TEST_AUTHOR = "Тургенев";
    private final String TEST_GENRE = "Драма";

    @Test
    public void findAllTest() {
        assertEquals(bookRep.findAll().size(), 0);
        insertBook();
        assertEquals(bookRep.findAll().size(), 1);
    }

    @Test
    public void getBookByAuthorTest() {
        insertBook();
        assertEquals(bookRep.getBookByAuthor(TEST_AUTHOR).get(0).getBookName(), TEST_BOOK_NAME1);
    }

    @Test
    public void getBookByGenreTest() {
        insertBook();
        assertEquals(bookRep.getBookByGenre(TEST_GENRE).get(0).getBookName(), TEST_BOOK_NAME1);
    }

    @Test
    public void getBookByNameTest() {
        insertBook();
        assertEquals(bookRep.findAll().get(0).getBookName(), TEST_BOOK_NAME1);
    }

    @Test
    public void deleteTest() {
        Book book = insertBook();
        bookRep.delete(book);
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