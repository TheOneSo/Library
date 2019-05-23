package com.oneso.library.services;

import com.oneso.library.domain.Author;
import com.oneso.library.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Optional;

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

        verify(aRepo, times(1)).save(any());
    }

    @Test
    @DisplayName("возвращает автора по имени")
    void shouldReturnAuthorByName() {
        when(aRepo.findById(anyString())).thenReturn(Optional.of(new Author()));

        assertNotNull(service.getAuthor("test"));
        verify(aRepo, times(1)).findById(anyString());
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
        service.deleteAuthor("qwe");

        verify(aRepo, times(1)).deleteById(anyString());
    }
}
