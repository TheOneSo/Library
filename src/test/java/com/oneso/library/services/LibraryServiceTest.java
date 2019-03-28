package com.oneso.library.services;

import com.oneso.library.dao.LibraryDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Сервис по работе с библиотекой")
class LibraryServiceTest {

    private LibraryService libraryService;

    @Mock
    private LibraryDao libraryDao;

    @BeforeEach
    void setUp() {
        libraryDao = mock(LibraryDao.class);
        libraryService = new LibraryServiceImpl(libraryDao);

        when(libraryDao.findIdAutor(anyString())).thenReturn(0);
        when(libraryDao.findIdGenre(anyString())).thenReturn(0);
    }

    @Test
    @DisplayName("возвращает все из базы")
    void shouldReturnAllInBD() {
        when(libraryDao.getAll()).thenReturn(new ArrayList<>());

        assertEquals("", libraryService.showAll());
    }

    @Test
    @DisplayName("возвращает все книги")
    void shouldReturnAllBook() {
        when(libraryDao.getAllBook()).thenReturn(new ArrayList<>());

        assertEquals("", libraryService.showAllBook());
    }

    @Test
    @DisplayName("возвращает всех авторов")
    void shouldReturnAllAutors() {
        when(libraryDao.getAllAutor()).thenReturn(new ArrayList<>());

        assertEquals("", libraryService.showAllAutors());
    }

    @Test
    @DisplayName("возвращает все жанры")
    void shouldReturnAllGenre() {
        when(libraryDao.getAllGenre()).thenReturn(new ArrayList<>());

        assertEquals("", libraryService.showAllGenres());
    }

    @Test
    @DisplayName("возвращает всю информацию по книге")
    void shouldReturnAllInfoToBook() {
        when(libraryDao.getByNameBook(anyString())).thenReturn(new ArrayList<>());

        assertEquals("", libraryService.showBook("test"));
    }

    @Test
    @DisplayName("создает нового автора")
    void shouldCreateNewAutor() {
        libraryService.createAutor("");

        verify(libraryDao, times(1)).findIdAutor("");
        verify(libraryDao, times(1)).insertAutor("");
    }

    @Test
    @DisplayName("создает новый жанр")
    void shouldCreateNewGenre() {
        libraryService.createGenre("");

        verify(libraryDao, times(1)).findIdGenre("");
        verify(libraryDao, times(1)).insertGenre("");
    }

    @Test
    @DisplayName("создает новую книгу")
    void shouldCreateNewBook() {
        when(libraryDao.findIdAutor(anyString())).thenReturn(1);
        when(libraryDao.findIdGenre(anyString())).thenReturn(1);

        libraryService.createBook("test", "test", "test");

        verify(libraryDao, times(1)).insertBook(anyString(), anyInt(), anyInt());
    }

    @Test
    @DisplayName("удаляет автора")
    void shouldDeleteAutor() {
        libraryService.deleteAutor("test");

        verify(libraryDao, times(1)).deleteByNameAutor(anyString());
    }

    @Test
    @DisplayName("удаляет жанр")
    void shouldDeleteGenre() {
        libraryService.deleteGenre("test");

        verify(libraryDao, times(1)).deleteByNameGenre("test");
    }

    @Test
    @DisplayName("удаляет книгу")
    void shouldDeleteBook() {
        libraryService.deleteBook("test");

        verify(libraryDao, times(1)).deleteByNameBook("test");
    }

}
