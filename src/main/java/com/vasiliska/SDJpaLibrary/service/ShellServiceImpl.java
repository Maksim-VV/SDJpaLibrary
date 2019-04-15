package com.vasiliska.SDJpaLibrary.service;


import com.vasiliska.SDJpaLibrary.domain.Author;
import com.vasiliska.SDJpaLibrary.domain.Book;
import com.vasiliska.SDJpaLibrary.domain.Comment;
import com.vasiliska.SDJpaLibrary.domain.Genre;
import com.vasiliska.SDJpaLibrary.repository.AuthorRep;
import com.vasiliska.SDJpaLibrary.repository.BookRep;
import com.vasiliska.SDJpaLibrary.repository.CommentRep;
import com.vasiliska.SDJpaLibrary.repository.GenreRep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@Transactional
public class ShellServiceImpl implements ShellService {

    private BookRep bookRep;
    private GenreRep genreRep;
    private AuthorRep authorRep;
    private CommentRep commentRep;

    private final String MSG_DONT_FIND = "Объект не найден!";
    private final String MSG_ADD_NEW_BOOK = "Книга \"%s\" добавлена";
    private final String MSG_ADD_NEW_COMMENT = "Комментарий на книгу \"%s\" добавлен";
    private final String MSG_DELETE_BOOK = "Книга \"%s\" удалена из библиотеки.";
    private final String MSG_DONT_ADD_BOOK = "Не удалось добавить книгу \"%s\"";

    @Autowired
    public ShellServiceImpl(BookRep bookRep, GenreRep genreRep, AuthorRep authorRep, CommentRep commentRep) {
        this.authorRep = authorRep;
        this.genreRep = genreRep;
        this.bookRep = bookRep;
        this.commentRep = commentRep;
    }

    public ShellServiceImpl() {
    }

    @Override
    @Transactional
    public String addNewBook(String bookName, String authorName, String genreName) {
        Genre genre = genreRep.getGenreByName(genreName);
        if (genre == null) {
            Genre genreNew = new Genre(genreName);
            genre = genreRep.save(genreNew);
        }

        Book book = new Book();
        book.setBookName(bookName);
        book.setGenre(genre);

        Author author = authorRep.getAuthorByName(authorName);
        if (author == null) {
            Author authorNew = new Author(authorName);
            author = authorRep.save(authorNew);
        }
        book.setAuthor(author);
        if (bookRep.save(book)==null) {
            return String.format(MSG_DONT_ADD_BOOK, bookName);
        }
        return String.format(MSG_ADD_NEW_BOOK, bookName);
    }

    @Override
    @Transactional
    public String delBook(String bookName) {
        List<Book> listBooks = bookRep.getBookByName(bookName);
        if (listBooks == null || listBooks.isEmpty()) {
            return MSG_DONT_FIND;
        }
        String authorName = listBooks.get(0).getAuthor().getAuthorName();
        List<Book> listBookByAuthor = bookRep.getBookByAuthor(authorName);
        if (listBookByAuthor.size() == 1) {
            authorRep.delete(listBookByAuthor.get(0).getAuthor());
        }

        String genreName = listBooks.get(0).getGenre().getGenreName();
        List<Book> listBookByGenre = bookRep.getBookByGenre(genreName);
        if (listBookByGenre.size() == 1) {
            genreRep.delete(listBookByGenre.get(0).getGenre());
        }

        bookRep.delete(listBooks.get(0));
        return String.format(MSG_DELETE_BOOK, bookName);
    }

    @Override
    @Transactional(readOnly = true)
    public String bookByGenre(String genreName) {
        return showBooks(bookRep.getBookByGenre(genreName));
    }

    @Override
    @Transactional(readOnly = true)
    public String bookByName(String bookName) {
        return showBooks(bookRep.getBookByName(bookName));
    }

    @Override
    @Transactional(readOnly = true)
    public String bookByAuthor(String authorName) {
       return showBooks(bookRep.getBookByAuthor(authorName));
    }

    @Override
    @Transactional(readOnly = true)
    public String showAllBooks() {
        return showBooks(bookRep.findAll());
    }

    @Override
    @Transactional
    public String addComment(String commentText, String bookName) {
        List<Book> listBooks = bookRep.getBookByName(bookName);
        if (listBooks == null || listBooks.isEmpty()) {
            return MSG_DONT_FIND;
        }
        Book book = listBooks.get(0);
        commentRep.save(new Comment(commentText, book));
        return String.format(MSG_ADD_NEW_COMMENT, bookName);
    }

    @Override
    @Transactional(readOnly = true)
    public String getCommentsByBook(String bookName) {
        List<Book> listBooks = bookRep.getBookByName(bookName);
        if (listBooks == null || listBooks.isEmpty()) {
            return MSG_DONT_FIND;
        }
        Book book = listBooks.get(0);
        return showComments(commentRep.getCommentByBook(book.getBookId()));
    }

    private String showBooks(List<Book> listBooks) {
        if (listBooks == null || listBooks.isEmpty()) {
            return MSG_DONT_FIND;
        }
        StringBuffer stringBuffer = new StringBuffer();
        listBooks.forEach(stringBuffer::append);
        return stringBuffer.toString();
    }

    private String showComments(List<Comment> listComments) {
        if (listComments == null || listComments.isEmpty()) {
            return MSG_DONT_FIND;
        }
        StringBuffer stringBuffer = new StringBuffer();
        listComments.forEach(stringBuffer::append);
        return stringBuffer.toString();
    }
}


