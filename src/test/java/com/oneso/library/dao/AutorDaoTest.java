package com.oneso.library.dao;

import com.oneso.library.domain.Autor;
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
@DisplayName("Дао по работе с авторами")
public class AutorDaoTest {

    @Autowired
    private AutorDao autorDao;

    @Test
    @DisplayName("добавляет нового автора")
    public void shouldAddNewAutor() {
        Autor expected = new Autor("newAutor");
        autorDao.insert(expected);

        assertEquals(expected.getName(), autorDao.findByName("newAutor").getName());
    }

    @Test
    @DisplayName("находит автора по id")
    public void shouldFindAutorById() {
        autorDao.insert(new Autor("findAutor"));

        assertNotNull(autorDao.findById(1));
    }

    @Test
    @DisplayName("находит автора по имени")
    public void shouldFindAutorByName() {
        autorDao.insert(new Autor("findAutorName"));

        assertEquals("findAutorName", autorDao.findByName("findAutorName").getName());
    }

    @Test
    @DisplayName("находит все книги от автора")
    public void shouldFindAllBooksForAutor() {
        assertNotNull(autorDao.findAllBookByAutor("testA"));
    }

    @Test
    @DisplayName("удаляет автора по имени")
    public void shouldDeleteAutorByname() {
        autorDao.insert(new Autor("deleteAutor"));
        int expected = autorDao.count() - 1;
        autorDao.deleteByName("deleteAutor");
        int actual = autorDao.count();

        assertEquals(expected, actual);
    }
}
