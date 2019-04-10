package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(AuthorRepositoryJpa.class)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с авторами")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    @DisplayName("добавляет нового автора")
    void shouldAddNewAuthor() {
        Author author = new Author("test");
        long expected = repository.count() + 1;
        repository.insert(author);

        assertThat(repository.count())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("находит автора по id")
    void shouldFindAuthorById() {
        Author actual = repository.findById(1);

        assertThat(actual)
                .isNotNull();
    }

    @Test
    @DisplayName("находит всех авторов")
    void shouldFindAllAuthorsById() {
        Author author = new Author("123");
        repository.insert(author);

        assertThat(repository.findAll())
                .isNotNull();
    }

    @Test
    @DisplayName("удаляет автора по id")
    void shouldDeleteAuthorById() {
        Author author = new Author("delA");
        long expected = repository.count();
        repository.insert(author);

        repository.deleteById(2);

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
