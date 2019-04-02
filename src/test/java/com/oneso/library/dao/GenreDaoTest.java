package com.oneso.library.dao;

import com.oneso.library.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
@ActiveProfiles("test")
@ComponentScan("com.oneso.library.dao")
@DisplayName("Дао по работе с жанрами")
public class GenreDaoTest {

    @Autowired
    private GenreDao genreDao;

    @Test
    @DisplayName("добавляет новый жанр")
    public void shouldAddNewGenre() {
        Genre expected = new Genre("newGenre");
        genreDao.insert(expected);

        assertEquals(expected.getName(), genreDao.findByName("newGenre").getName());
    }

    @Test
    @DisplayName("находит жанр по id")
    public void shouldFindGenreById() {
        genreDao.insert(new Genre("findGenre"));

        assertNotNull(genreDao.findById(1));
    }

    @Test
    @DisplayName("находит genre по имени")
    public void shouldFindGenreByName() {
        genreDao.insert(new Genre("findGenreName"));

        assertEquals("findGenreName", genreDao.findByName("findGenreName").getName());
    }

    @Test
    @DisplayName("находит все жанры")
    public void shouldFindAllGenres() {
        assertNotNull(genreDao.findAll());
    }

    @Test
    @DisplayName("удаляет жанр по имени")
    public void shouldDeleteGenreByname() {
        genreDao.insert(new Genre("deleteGenre"));
        int expected = genreDao.count() - 1;
        genreDao.deleteByName("deleteGenre");
        int actual = genreDao.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("удаляет жанр по id")
    public void shouldDeleteGenreById() {
        genreDao.insert(new Genre("deleteId"));
        Genre genre = genreDao.findByName("deleteId");
        int expected = genreDao.count() - 1;
        genreDao.deleteById(genre.getId());
        int actual = genreDao.count();

        assertEquals(expected, actual);
    }
}
