package com.oneso.library.dao;

import com.oneso.library.domain.Autor;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@ComponentScan
@ActiveProfiles("test")
@DisplayName("Дао по работе с книгами")
public class BookDaoTest {

    @Autowired
    private BookDao bookDao;

    @Test
    @DisplayName("добавляет новую книгу")
    public void shouldAddNewBook() {
        Book expected = new Book("newBook", new Autor(1, "q"), new Genre(1, "w"));
        bookDao.insert(expected);

        assertEquals(expected.getName(), bookDao.findByName("newBook").getName());
    }

    @Test
    @DisplayName("находит книгу по id")
    public void shouldFindBookById() {
        assertNotNull(bookDao.findById(1));
    }

    @Test
    @DisplayName("находит книгу по имени")
    public void shouldFindBookByName() {
        assertEquals("testB", bookDao.findByName("testB").getName());
    }

    @Test
    @DisplayName("находит все книги")
    public void shouldFindAllBooks() {
        assertNotNull(bookDao.findAll());
    }

    @Test
    @DisplayName("удаляет книгу по имени")
    public void shouldDeleteBookByname() {
        bookDao.insert(new Book("deleteBook", new Autor(1, "aB"), new Genre(1, "gB")));
        int expected = bookDao.count() - 1;
        bookDao.deleteByName("deleteBook");
        int actual = bookDao.count();

        assertEquals(expected, actual);
    }
}
