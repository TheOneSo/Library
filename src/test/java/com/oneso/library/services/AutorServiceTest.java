package com.oneso.library.services;

import com.oneso.library.dao.AutorDao;
import com.oneso.library.domain.Autor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@DisplayName("Сервис по работе с авторами")
class AutorServiceTest {

    private AutorService service;

    @Mock
    private AutorDao autorDao;

    @BeforeEach
    void setUp() {
        autorDao = mock(AutorDao.class);
        service = new AutorServiceImpl(autorDao);
    }

    @Test
    @DisplayName("добовляет нового автора")
    void shouldAddNewAutor() {
        service.addAutor("test");

        verify(autorDao, times(1)).insert(any());
    }

    @Test
    @DisplayName("достает автора из BD")
    void shouldGetAutorForBD() {
        Autor _autor = new Autor("test");
        when(autorDao.findByName(anyString())).thenReturn(_autor);

        assertEquals(_autor, service.getAutor("test"));
    }

    @Test
    @DisplayName("получает список авторов")
    void shouldGetListAutors() {
        service.showAllAutors();

        verify(autorDao, times(1)).findAll();
    }

    @Test
    @DisplayName("получает всю информацию об авторе")
    void shouldGetAllInfoForAutor() {
        String temp = "test";
        when(autorDao.findByName(temp)).thenReturn(new Autor(temp));
        when(autorDao.findAllBookByAutor(temp)).thenReturn(new ArrayList<>());

        service.showAllInfoAutor(temp);

        verify(autorDao, times(1)).findByName(temp);
        verify(autorDao, times(1)).findAllBookByAutor(temp);
    }

    @Test
    @DisplayName("удаляет автора")
    void shouldDeleteAutor() {
        service.deleteAutor("test");

        verify(autorDao, times(1)).deleteByName("test");
    }
}
