package com.oneso.library.services;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import com.oneso.library.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Сервис по работе с книгами")
class BookServiceTest {

    private BookService service;

    @Mock
    private BookRepository bRepo;

    private Book testBook;

    @BeforeEach
    void setUp() {
        bRepo = mock(BookRepository.class);
        service = new BookServiceImpl(bRepo);

        testBook = new Book("test", new Author("test"), new Genre("test"));
    }

    @Test
    @DisplayName("добавляет новую книгу")
    void shouldAddNewBook() {
        service.addBook("test", "qwe", "qwe");

        verify(bRepo, times(1)).save(any());
    }

    @Test
    @DisplayName("возвращает список всех книг")
    void shouldReturnListAllBook() {
        List<Book> books = Collections.singletonList(testBook);
        when(bRepo.findAll()).thenReturn(books);

        assertNotNull(service.getAllBooks());
        verify(bRepo, times(1)).findAll();
    }

    @Test
    @DisplayName("возвращает книгу по имени")
    void shouldReturnBookByName() {
        when(bRepo.findById(anyString())).thenReturn(Optional.of(testBook));

        assertNotNull(service.getBook("test"));
        verify(bRepo, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("возвращает все книги автора")
    void shouldReturnAllBooksAuthor() {
        List<Book> books = Collections.singletonList(testBook);
        when(bRepo.findBookByAuthorId(anyString())).thenReturn(books);

        assertNotNull(service.getAllBookByAuthorId("asd"));
        verify(bRepo, times(1)).findBookByAuthorId(anyString());
    }

    @Test
    @DisplayName("возвращает все книги по жанру")
    void shouldReturnAllBooksForGenre() {
        List<Book> books = Collections.singletonList(testBook);
        when(bRepo.findBookByGenreId(anyString())).thenReturn(books);

        assertNotNull(service.getAllBookByGenreId("asd"));
        verify(bRepo, times(1)).findBookByGenreId(anyString());
    }

    @Test
    @DisplayName("удаляет книгу")
    void shouldDeleteBook() {
        service.deleteBook("qwe");

        verify(bRepo, times(1)).deleteById(anyString());
    }
}
