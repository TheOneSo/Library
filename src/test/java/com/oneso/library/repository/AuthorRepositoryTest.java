package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с авторами")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("добавляет нового автора")
    void shouldAddNewAuthor() {
        Author author = new Author("test");
        repository.insert(author);

        long author_id = em.getId(author, Long.class);

        assertThat(repository.findById(author_id).getName())
                .isEqualTo(author.getName());
    }

    @Test
    @DisplayName("находит автора по id")
    void shouldFindAuthorById() {
        Author author = new Author("author");
        em.persist(author);
        em.flush();

        long author_id = em.getId(author, Long.class);

        Author actual = repository.findById(author_id);

        assertThat(actual.getName())
                .isEqualTo(author.getName());
    }

    @Test
    @DisplayName("находит всех авторов")
    void shouldFindAllAuthorsById() {
        List<Author> authors = repository.findAll();

        assertThat(authors.get(0).getName())
                .isEqualTo("testA");
    }

    @Test
    @DisplayName("удаляет автора по id")
    void shouldDeleteAuthorById() {
        Author author = new Author("delA");
        long expected = repository.count();
        em.persist(author);
        em.flush();

        long author_id = em.getId(author, Long.class);

        repository.deleteById(author_id);

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
