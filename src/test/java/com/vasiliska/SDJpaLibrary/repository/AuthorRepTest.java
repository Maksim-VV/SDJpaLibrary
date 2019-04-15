package com.vasiliska.SDJpaLibrary.repository;

import com.vasiliska.SDJpaLibrary.domain.Author;
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
public class AuthorRepTest {

    private static final String TEST_AUTHOR_NAME1 = "Булгаков";
    private static final String TEST_AUTHOR_NAME2 = "Толкиен";

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRep authorRep;

    @Test
    public void getAuthorByNameTest() {
        insertAuthorTest(TEST_AUTHOR_NAME1);
        assertThat(authorRep.getAuthorByName(TEST_AUTHOR_NAME1).getAuthorName().equals(TEST_AUTHOR_NAME1));
    }

    @Test
    public void addNewAuthorTest() {
        Author author = new Author();
        author.setAuthorName(TEST_AUTHOR_NAME1);
        authorRep.save(author);

        assertThat(authorRep.getAuthorByName(TEST_AUTHOR_NAME1).getAuthorName().equals(TEST_AUTHOR_NAME1));

    }

    @Test
    public void deleteTest() {
        insertAuthorTest(TEST_AUTHOR_NAME2);

        Author author2 = authorRep.getAuthorByName(TEST_AUTHOR_NAME2);
        assertThat(author2.getAuthorName().equals(TEST_AUTHOR_NAME2));

        authorRep.delete(author2);
        assertNull(authorRep.getAuthorByName(TEST_AUTHOR_NAME2));
    }

    private void insertAuthorTest(String testName) {
        Author author = new Author();
        author.setAuthorName(testName);
        entityManager.persist(author);
    }
}