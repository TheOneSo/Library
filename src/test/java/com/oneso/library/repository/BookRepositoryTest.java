package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataMongoTest
@ComponentScan({"com.oneso.library.repository", "com.oneso.library.events", "com.oneso.library.mongoConfig"})
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с книгами")
class BookRepositoryTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private MongoTemplate template;

    @Test
    @DisplayName("добавляет новую книгу")
    void shouldAddNewBook() {
        Book book = new Book("test", "111",
                new Author("test", "111"), new Genre("test", "111"));
        repository.save(book);

        assertThat(template.findById("111", Book.class))
                .isNotNull().isEqualToComparingOnlyGivenFields(book,
                "name", "genre.name", "author.name");
    }

    @Test
    @DisplayName("находит книгу")
    void shouldFindBook() {
        Optional<Book> actual = repository.findById("1");

        assertThat(actual.get().getId())
                .isEqualTo("1");

        assertThat(actual.get().getAuthor().getId())
                .isEqualTo("1");

        assertThat(actual.get().getGenre().getId())
                .isEqualTo("1");
    }

    @Test
    @DisplayName("находит все книги")
    void shouldFindAllBooks() {
        List<Book> books = repository.findAll();

        assertThat(books).allSatisfy(book -> assertThat(book).isNotNull());
    }

    @Test
    @DisplayName("находит все книги автора")
    void shouldFindAllBookByAuthor() {
        List<Book> actuals = repository.findBookByAuthorId("1");

        assertThat(actuals).allSatisfy(book -> assertThat(book.getName()).isEqualTo("testB"));
    }

    @Test
    @DisplayName("находит все книги по жанру")
    void shouldFindAllBookByGenre() {
        List<Book> actuals = repository.findBookByGenreId("1");

        assertThat(actuals).allSatisfy(book -> assertThat(book.getName()).isEqualTo("testB"));
    }

    @Test
    @DisplayName("удаляет книгу")
    void shouldDeleteBook() {
        Book book = new Book("delete", "2",
                new Author("author", "123"), new Genre("genre", "123"));
        long expected = repository.count();
        template.save(book);

        repository.deleteById(book.getId());

        assertThat(repository.count())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("удаляет книгу по автору")
    void shouldDeleteBookByAuthor() {
        Book book = new Book("deleteA", "123",
                new Author("author", "qwerty"), new Genre("genre", "123"));
        long expected = repository.count();
        template.save(book);

        repository.deleteBookByAuthorId(book.getAuthor().getId());

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
