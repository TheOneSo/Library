package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
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
        repository.save(author);

        long author_id = em.getId(author, Long.class);

        assertThat(repository.findById(author_id).get().getName())
                .isEqualTo(author.getName());
    }

    @Test
    @DisplayName("находит автора по id")
    void shouldFindAuthorById() {
        Author author = new Author("author");
        em.persist(author);
        em.flush();

        Optional<Author> actual = repository.findAuthorByName(author.getName());

        assertThat(actual.get().getName())
                .isEqualTo(author.getName());
    }

    @Test
    @DisplayName("находит всех авторов")
    void shouldFindAllAuthorsById() {
        List<Author> authors = repository.findAll(Sort.by(Sort.Order.asc("name")));

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
