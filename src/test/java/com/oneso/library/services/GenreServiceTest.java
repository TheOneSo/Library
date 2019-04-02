package com.oneso.library.services;

import com.oneso.library.dao.GenreDao;
import com.oneso.library.domain.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Сервис по работе с жанрами")
class GenreServiceTest {

    private GenreService genreService;

    @Mock
    private GenreDao genreDao;

    @BeforeEach
    void setUp() {
        genreDao = mock(GenreDao.class);
        genreService = new GenreServiceImpl(genreDao);
    }

    @Test
    @DisplayName("добавляет новый жанр")
    void shouldAddNewGenre() {
        genreService.addGenre("test");

        verify(genreDao, times(1)).insert(any());
    }

    @Test
    @DisplayName("показывает все жанры")
    void shouldShowAllGenres() {
        genreService.showAllGenre();

        verify(genreDao, times(1)).findAll();
    }

    @Test
    @DisplayName("возвращает жанр")
    void shouldReturnGenre() {
        Genre genre = new Genre("test");
        when(genreDao.findByName("test")).thenReturn(genre);

        assertEquals(genre, genreService.getGenre("test"));
    }

    @Test
    @DisplayName("возвращает жанр по id")
    void shouldReturnGenreById() {
        Genre genre = new Genre("test");
        when(genreDao.findById(anyLong())).thenReturn(genre);

        assertEquals(genre, genreService.getGenre(1));
    }

    @Test
    @DisplayName("удаляет жанр")
    void shouldDeleteGenre() {
        genreService.deleteGenre(1);

        verify(genreDao, times(1)).deleteById(1);
    }
}
