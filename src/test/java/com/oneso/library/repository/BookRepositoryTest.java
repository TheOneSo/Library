package com.oneso.library.repository;

import com.oneso.library.domain.Book;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(BookRepositoryJpa.class)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с книгами")
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    @DisplayName("добавляет новую книгу")
    void shouldAddNewBook() {
        Book book = new Book("test", null, null);
        long expected = repository.count() + 1;

        repository.insert(book);

        assertThat(repository.count())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("находит книгу по id")
    void shouldFindBookById() {
        Book book = repository.findById(1);

        assertThat(book)
                .isNotNull();
    }

    @Test
    @DisplayName("находит все книги")
    void shouldFindAllBooks() {
        List<Book> books = repository.findAll();

        assertThat(books)
                .isNotNull();
    }

    @Test
    @DisplayName("находит все книги по id автора")
    void shouldFindAllBookByAuthorId() {
        List<Book> books = repository.findAllBookByAuthorId(1);

        assertThat(books)
                .isNotNull();
    }

    @Test
    @DisplayName("находит все книги по id жанра")
    void shouldFindAllBookByGenreId() {
        List<Book> books = repository.findAllBookByGenreId(1);

        assertThat(books)
                .isNotNull();
    }

    @Test
    @DisplayName("удаляет книгу по id")
    void shouldDeleteBookById() {
        Book book = new Book("delete", null, null);
        long expected = repository.count();
        repository.deleteById(2);

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
