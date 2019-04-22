package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataMongoTest
@ComponentScan({"com.oneso.library.repository", "com.oneso.library.events"})
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с книгами")
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Test
    @DisplayName("добавляет новую книгу")
    void shouldAddNewBook() {
        Book book = new Book("test", new Author("qwe"), new Genre("qwe"));
        repository.save(book);

        assertThat(repository.findBookByName("test").get().getName())
                .isEqualTo(book.getName());
    }

    @Test
    @DisplayName("находит книгу")
    void shouldFindBook() {
        Book book = new Book("book", new Author("qwe"), new Genre("qwe"));
        repository.save(book);

        Optional<Book> actual = repository.findBookByName("book");

        assertThat(actual.get().getName())
                .isEqualTo(book.getName());

        assertThat(actual.get().getAuthor().getName())
                .isEqualTo(book.getAuthor().getName());

        assertThat(actual.get().getGenre().getName())
                .isEqualTo(book.getGenre().getName());
    }

    @Test
    @DisplayName("находит все книги")
    void shouldFindAllBooks() {
        repository.save(new Book("qwe", new Author("qwe"), new Genre("qwe")));
        List<Book> books = repository.findAll();

        assertThat(books.get(0))
                .isNotNull();
    }

    @Test
    @DisplayName("находит все книги автора")
    void shouldFindAllBookByAuthor() {
        Author author = new Author("author");
        Book book = new Book("author book", author, new Genre("qwe"));
        repository.save(book);

        List<Book> actuals = repository.findBookByAuthorName(author.getName());

        assertThat(actuals.get(0).getName())
                .isEqualTo(book.getName());
    }

    @Test
    @DisplayName("находит все книги по жанру")
    void shouldFindAllBookByGenre() {
        Genre genre = new Genre("genre");
        Book book = new Book("genre book", new Author("qwe"), genre);
        repository.save(book);

        List<Book> actuals = repository.findBookByGenreName(genre.getName());

        assertThat(actuals.get(0).getName())
                .isEqualTo(book.getName());
    }

    @Test
    @DisplayName("удаляет книгу")
    void shouldDeleteBook() {
        Book book = new Book("delete", new Author("author"), new Genre("genre"));
        long expected = repository.count();
        repository.save(book);

        repository.deleteBookByName(book.getName());

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
