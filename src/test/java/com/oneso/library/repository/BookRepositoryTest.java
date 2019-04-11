package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("добавляет новую книгу")
    void shouldAddNewBook() {
        Book book = new Book("test", null, null);
        repository.insert(book);

        long book_id = em.getId(book, Long.class);

        assertThat(repository.findById(book_id).getName())
                .isEqualTo(book.getName());
    }

    @Test
    @DisplayName("находит книгу по id")
    void shouldFindBookById() {
        Book book = new Book("book", new Author(1), new Genre(1));
        em.persist(book);
        em.flush();

        long book_id = em.getId(book, Long.class);

        Book actual = repository.findById(book_id);

        assertThat(actual.getName())
                .isEqualTo(book.getName());

        assertThat(actual.getAuthor().getId())
                .isEqualTo(book.getAuthor().getId());

        assertThat(actual.getGenre().getId())
                .isEqualTo(book.getGenre().getId());
    }

    @Test
    @DisplayName("находит все книги")
    void shouldFindAllBooks() {
        assertThat(repository.findAll())
                .isNotNull();
    }

    @Test
    @DisplayName("находит все книги по id автора")
    void shouldFindAllBookByAuthorId() {
        Author author = new Author("author");
        em.persistAndFlush(author);
        long author_id = em.getId(author, Long.class);

        Book book = new Book("author book", new Author(author_id), null);
        em.persistAndFlush(book);

        List<Book> actuals = repository.findAllBookByAuthorId(author_id);

        assertThat(actuals.get(0).getName())
                .isEqualTo(book.getName());
    }

    @Test
    @DisplayName("находит все книги по id жанра")
    void shouldFindAllBookByGenreId() {
        Genre genre = new Genre("genre");
        em.persistAndFlush(genre);
        long genre_id = em.getId(genre, Long.class);

        Book book = new Book("genre book", null, new Genre(genre_id));
        em.persistAndFlush(book);

        List<Book> actuals = repository.findAllBookByGenreId(genre_id);

        assertThat(actuals.get(0).getName())
                .isEqualTo(book.getName());
    }

    @Test
    @DisplayName("удаляет книгу по id")
    void shouldDeleteBookById() {
        Book book = new Book("delete", null, null);
        long expected = repository.count();
        em.persist(book);
        em.flush();

        long book_id = em.getId(book, Long.class);

        repository.deleteById(book_id);

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
