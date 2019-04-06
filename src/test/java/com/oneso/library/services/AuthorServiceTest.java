package com.oneso.library.services;

import com.oneso.library.domain.Author;
import com.oneso.library.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DisplayName("Сервис по работе с авторами")
class AuthorServiceTest {

    private AuthorService service;

    @Mock
    private AuthorRepository aRepo;

    @BeforeEach
    void setUp() {
        aRepo = mock(AuthorRepository.class);
        service = new AuthorServiceImpl(aRepo);
    }

    @Test
    @DisplayName("добовляет нового автора")
    void shouldAddNewAuthor() {
        service.addAuthor("test");

        verify(aRepo, times(1)).insert(any());
    }

    @Test
    @DisplayName("достает автора из BD по id")
    void shouldGetAuthorFirBDById() {
        when(aRepo.findById(anyLong())).thenReturn(new Author("test"));

        assertNotNull(service.getAuthor(1));
        verify(aRepo, times(1)).findById(anyLong());
    }

    @Test
    @DisplayName("получает список авторов")
    void shouldGetListAuthors() {
        service.getAllAuthors();

        verify(aRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("удаляет автора")
    void shouldDeleteAuthor() {
        service.deleteAuthor(1);

        verify(aRepo, times(1)).deleteById(1);
    }
}
