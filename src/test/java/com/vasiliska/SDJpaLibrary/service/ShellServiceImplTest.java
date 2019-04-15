package com.vasiliska.SDJpaLibrary.service;

import com.vasiliska.SDJpaLibrary.repository.BookRep;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false",
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
})
public class ShellServiceImplTest {

    @MockBean
    private BookRep bookRep;

    @Autowired
    ShellServiceImpl shellService;

    private final String TEST_BOOK_NAME = "Айвенго";
    private final String TEST_AUTHOR = "В.Скотт";
    private final String TEST_GENRE = "Роман";
    private final String TEST_COMMENT = "Книга супер!";

    @Test
    public void addNewBook() {
        assertTrue(shellService.addNewBook(TEST_BOOK_NAME, TEST_AUTHOR, TEST_GENRE).contains(TEST_BOOK_NAME));
    }

    @Test
    public void bookByGenre() {
        shellService.bookByGenre(TEST_GENRE);
        verify(bookRep).getBookByGenre(TEST_GENRE);
    }

    @Test
    public void bookByName() {
        shellService.bookByName(TEST_BOOK_NAME);
        verify(bookRep).getBookByName(TEST_BOOK_NAME);
    }

    @Test
    public void bookByAuthor() {
        shellService.bookByAuthor(TEST_AUTHOR);
        verify(bookRep).getBookByAuthor(TEST_AUTHOR);
    }

    @Test
    public void showAllBooks() {
        shellService.showAllBooks();
        verify(bookRep).findAll();
    }

    @Test
    public void addComment() {
        shellService.addComment(TEST_COMMENT, TEST_BOOK_NAME);
        verify(bookRep).getBookByName(TEST_BOOK_NAME);
    }

    @Test
    public void getCommentByBook() {
        shellService.getCommentsByBook(TEST_BOOK_NAME);
        verify(bookRep).getBookByName(TEST_BOOK_NAME);
    }

}