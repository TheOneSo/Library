package com.oneso.library.services;

import com.oneso.library.dao.BookDao;
import com.oneso.library.domain.Autor;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@DisplayName("Сервис по работе с книгами")
class BookServiceTest {

    private BookService bookService;

    @Mock
    private BookDao bookDao;

    @BeforeEach
    void setUp() {
        bookDao = mock(BookDao.class);
        bookService = new BookServiceImpl(bookDao);
    }

    @Test
    @DisplayName("добавляет новую книгу")
    void shouldAddNewBook() {
        bookService.addBook("test", new Autor("test"), new Genre("test"));

        verify(bookDao, times(1)).insert(any());
    }

    @Test
    @DisplayName("показывает все книги")
    void shouldShowAllBooks() {
        bookService.showAllBook();

        verify(bookDao, times(1)).findAll();
    }

    @Test
    @DisplayName("показывает всю информацию по книге")
    void shouldShowAllInfoForBook() {
        when(bookDao.findByName(anyString())).thenReturn(new Book("test", new Autor("test"), new Genre("test")));
        bookService.showAllInfoToBook("test");

        verify(bookDao, times(1)).findByName("test");
    }

    @Test
    @DisplayName("удаляет книгу")
    void shouldDeleteBook() {
        bookService.deleteBook("test");

        verify(bookDao, times(1)).deleteByName("test");
    }
}
