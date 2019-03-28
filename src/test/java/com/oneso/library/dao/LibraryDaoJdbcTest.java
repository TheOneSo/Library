package com.oneso.library.dao;

import com.oneso.library.domain.Library;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@ComponentScan
@ActiveProfiles("test")
@DisplayName("Дао по работе с библиотекой")
class LibraryDaoJdbcTest {

    @Autowired
    private LibraryDao libraryDao;

    @Test
    @DisplayName("возвращает список с библиотеки")
    void shouldReturnListWithLibrary() {
        assertNotNull(libraryDao.getAll());
    }

    @Test
    @DisplayName("возвращает все книги")
    void shouldReturnAllBook() {
        String expected = "testB";
        List<String> actual = libraryDao.getAllBook();

        assertTrue(actual.contains(expected));

    }

    @Test
    @DisplayName("возвращает всех авторов")
    void shouldReturnAllAutors() {
        String expected = "testA";
        List<String> actual = libraryDao.getAllAutor();

        assertTrue(actual.contains(expected));
    }

    @Test
    @DisplayName("возвращает все жанры")
    void shouldReturnAllGenre() {
        String expected = "testG";
        List<String> actual = libraryDao.getAllGenre();

        assertTrue(actual.contains(expected));
    }

    @Test
    @DisplayName("находит автора по id")
    void shouldFindAutorById() {
        String expected = "testA";
        List<Library> actual = libraryDao.getByIdAutor(1);
        Library temp = actual.get(0);

        assertEquals(expected, temp.getAutor());
    }

    @Test
    @DisplayName("находит жанр по id")
    void shouldFindGenreById() {
        String expected = "testG";
        List<Library> actual = libraryDao.getByIdGenre(1);
        Library temp = actual.get(0);

        assertEquals(expected, temp.getGenre());
    }

    @Test
    @DisplayName("находит книгу по имени")
    void shouldFindBookByName() {
        List<Library> actual = libraryDao.getByNameBook("testG");

        assertNotNull(actual);
    }

    @Test
    @DisplayName("возвращает id автора по имени")
    void shouldReturnIdAutorByName() {
        assertEquals(1, libraryDao.findIdAutor("testA"));
    }

    @Test
    @DisplayName("возвращает id жанра по имени")
    void shouldReturnIdGenreByName() {
        assertEquals(1, libraryDao.findIdGenre("testG"));
    }

    @Test
    @DisplayName("возвращает id книги по имени")
    void shouldReturnIdBookByName() {
        assertEquals(1, libraryDao.findIdBook("testB"));
    }

    @Test
    @DisplayName("добавляет новго автора")
    void shouldAddNewAutor() {
        libraryDao.insertAutor("autor");
        List<String> actual = libraryDao.getAllAutor();

        assertTrue(actual.size() > 1);
    }

    @Test
    @DisplayName("добавляет новый жанр")
    void shouldAddNewGenre() {
        libraryDao.insertGenre("genre");
        List<String> actual = libraryDao.getAllGenre();

        assertTrue(actual.size() > 1);
    }

    @Test
    @DisplayName("добавляет новую книгу")
    void shouldAddNewBook() {
        libraryDao.insertBook("book", 1, 1);
        List<String> actual = libraryDao.getAllBook();

        assertTrue(actual.size() > 1);
    }

    @Test
    @DisplayName("удалят книгу по id")
    void shouldDeleteBookById() {
        libraryDao.insertBook("testBook", 1, 1);
        int temp = libraryDao.findIdBook("testBook");
        int expected = libraryDao.getAllBook().size() - 1;
        libraryDao.deleteByIdBook(temp);
        int actual = libraryDao.getAllBook().size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("удаляет автора по id")
    void shouldDeleteAutorById() {
        libraryDao.insertAutor("testAutor");
        int temp = libraryDao.findIdAutor("testAutor");
        int expected = libraryDao.getAllAutor().size() - 1;
        libraryDao.deleteByIdAutor(temp);
        int acual = libraryDao.getAllAutor().size();

        assertEquals(expected, acual);
    }

    @Test
    @DisplayName("удаляет жанр по id")
    void shouldDeleteGenreById() {
        libraryDao.insertGenre("testGenre");
        int temp = libraryDao.findIdGenre("testGenre");
        int expected = libraryDao.getAllGenre().size() - 1;
        libraryDao.deleteByIdGenre(temp);
        int acual = libraryDao.getAllGenre().size();

        assertEquals(expected, acual);
    }

    @Test
    @DisplayName("удаляет автора по имени")
    void shouldDeleteAutorByName() {
        String temp = "helloAutor";
        libraryDao.insertAutor(temp);
        libraryDao.deleteByNameAutor(temp);

        assertFalse(libraryDao.getAllAutor().contains(temp));
    }

    @Test
    @DisplayName("удаляет жанр по имени")
    void shouldDeleteGenreByName() {
        String temp = "helloGenre";
        libraryDao.insertGenre(temp);
        libraryDao.deleteByNameGenre(temp);

        assertFalse(libraryDao.getAllGenre().contains(temp));
    }

    @Test
    @DisplayName("удаляет книгу по имени")
    void shouldDeleteBookByName() {
        String temp = "hellobook";
        libraryDao.insertBook(temp, 1, 1);
        libraryDao.deleteByNameBook(temp);

        assertFalse(libraryDao.getAllBook().contains(temp));
    }
}
