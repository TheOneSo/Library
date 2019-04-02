package com.oneso.library.dao;

import com.oneso.library.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@ComponentScan("com.oneso.library.dao")
@DisplayName("Дао по работе с авторами")
public class AuthorDaoTest {

    @Autowired
    private AuthorDao authorDao;

    @Test
    @DisplayName("добавляет нового автора")
    public void shouldAddNewAuthor() {
        Author expected = new Author("newAuthor");
        authorDao.insert(expected);

        assertEquals(expected.getName(), authorDao.findByName("newAuthor").getName());
    }

    @Test
    @DisplayName("находит автора по id")
    public void shouldFindAuthorById() {
        authorDao.insert(new Author("findAuthor"));

        assertNotNull(authorDao.findById(1));
    }

    @Test
    @DisplayName("находит автора по имени")
    public void shouldFindAuthorByName() {
        authorDao.insert(new Author("findAuthorName"));

        assertEquals("findAuthorName", authorDao.findByName("findAuthorName").getName());
    }

    @Test
    @DisplayName("находит всех авторов")
    public void shouldFindAllAuthors() {
        List<Author> authors = authorDao.findAll();

        assertNotNull(authors);
    }

    @Test
    @DisplayName("удаляет автора по имени")
    public void shouldDeleteAuthorByname() {
        authorDao.insert(new Author("deleteAuthor"));
        int expected = authorDao.count() - 1;
        authorDao.deleteByName("deleteAuthor");
        int actual = authorDao.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("удаляет автора по id")
    public void shouldDeleteAuthorById() {
        authorDao.insert(new Author("deleteId"));
        Author author = authorDao.findByName("deleteId");
        int expected = authorDao.count() - 1;
        authorDao.deleteById(author.getId());
        int actual = authorDao.count();

        assertEquals(expected, actual);
    }
}
