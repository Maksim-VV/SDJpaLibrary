package com.vasiliska.SDJpaLibrary.repository;

import com.vasiliska.SDJpaLibrary.domain.Genre;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class GenreRepTest {

    private static final String TEST_GENRE_NAME1 = "Драма";
    private static final String TEST_GENRE_NAME2 = "Фантастика";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private GenreRep genreRep;

    @Test
    public void deleteTest() {
        insertGenreTest(TEST_GENRE_NAME2);

        Genre genre2 = genreRep.getGenreByName(TEST_GENRE_NAME2);
        assertThat(genre2.getGenreId().equals(TEST_GENRE_NAME2));

        genreRep.delete(genre2);
        assertNull(genreRep.getGenreByName(TEST_GENRE_NAME2));
    }

    @Test
    public void getGenreByName() {

        insertGenreTest(TEST_GENRE_NAME1);
        assertThat(genreRep.getGenreByName(TEST_GENRE_NAME1).getGenreName().equals(TEST_GENRE_NAME1));
    }

    private void insertGenreTest(String testName) {
        Genre genre = new Genre();
        genre.setGenreName(testName);
        entityManager.persist(genre);
    }
}