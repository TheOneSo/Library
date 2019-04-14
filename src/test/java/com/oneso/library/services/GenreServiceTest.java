package com.oneso.library.services;

import com.oneso.library.domain.Genre;
import com.oneso.library.repository.GenreRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DisplayName("Сервис по работе с жанрами")
class GenreServiceTest {

    private GenreService service;

    @Mock
    private GenreRepository gRepo;

    private Genre genreTest;

    @BeforeEach
    void setUp() {
        gRepo = mock(GenreRepository.class);
        service = new GenreServiceImpl(gRepo);
        genreTest = new Genre("test");
    }

    @Test
    @DisplayName("добавляет новый жанр")
    void shouldAddNewGenre() {
        service.addGenre("test");

        verify(gRepo, times(1)).save(any());
    }

    @Test
    @DisplayName("возвращает все жанры")
    void shouldReturnAllGenres() {
        List<Genre> genres = Collections.singletonList(genreTest);
        when(gRepo.findAll()).thenReturn(genres);

        assertNotNull(service.getAllGenres());
        verify(gRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("возвращает жанр по id")
    void shouldReturnGenreById() {
        when(gRepo.findById(anyLong())).thenReturn(Optional.of(genreTest));

        assertNotNull(service.getGenre(1));
        verify(gRepo, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("возвращает жанр по имени")
    void shouldReturnGenreByName() {
        when(gRepo.findGenreByName(anyString())).thenReturn(Optional.of(genreTest));

        assertNotNull(service.getGenre("test"));
        verify(gRepo, times(1)).findGenreByName(anyString());
    }

    @Test
    @DisplayName("удаляет жанр")
    void shouldDeleteGenre() {
        service.deleteGenre(1);

        verify(gRepo, times(1)).deleteById(anyLong());
    }
}
