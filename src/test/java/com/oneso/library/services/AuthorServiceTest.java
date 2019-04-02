package com.oneso.library.services;

import com.oneso.library.dao.AuthorDao;
import com.oneso.library.domain.Author;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Сервис по работе с авторами")
class AuthorServiceTest {

    private AuthorService service;

    @Mock
    private AuthorDao authorDao;

    @BeforeEach
    void setUp() {
        authorDao = mock(AuthorDao.class);
        service = new AuthorServiceImpl(authorDao);
    }

    @Test
    @DisplayName("добовляет нового автора")
    void shouldAddNewAuthor() {
        service.addAuthor("test");

        verify(authorDao, times(1)).insert(any());
    }

    @Test
    @DisplayName("достает автора из BD по имени")
    void shouldGetAuthorForBDByName() {
        Author author = new Author("test");
        when(authorDao.findByName(anyString())).thenReturn(author);

        assertEquals(author, service.getAuthor("test"));
    }

    @Test
    @DisplayName("достает автора из BD по id")
    void shouldGetAuthorFirBDById() {
        Author author = new Author("test");
        when(authorDao.findById(anyLong())).thenReturn(author);

        assertEquals(author, service.getAuthor(1));
    }

    @Test
    @DisplayName("получает список авторов")
    void shouldGetListAuthors() {
        service.showAllAuthors();

        verify(authorDao, times(1)).findAll();
    }

    @Test
    @DisplayName("удаляет автора")
    void shouldDeleteAuthor() {
        service.deleteAuthor(1);

        verify(authorDao, times(1)).deleteById(1);
    }
}
