package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataMongoTest
@ComponentScan({"com.oneso.library.repository", "com.oneso.library.events"})
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с авторами")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Test
    @DisplayName("добавляет нового автора")
    void shouldAddNewAuthor() {
        Author author = new Author("test");
        repository.save(author);

        assertThat(repository.findAuthorByName("test").get().getName())
                .isEqualTo(author.getName());
    }


    @Test
    @DisplayName("находит всех авторов")
    void shouldFindAllAuthorsById() {
        Author author = new Author("qwe");
        repository.save(author);

        List<Author> authors = repository.findAll();

        assertThat(authors.get(0))
                .isNotNull();
    }

    @Test
    @DisplayName("удаляет автора")
    void shouldDeleteAuthor() {
        Author author = new Author("delA");
        long expected = repository.count();
        repository.save(author);

        Author actual = repository.findAuthorByName("delA").get();

        repository.deleteAuthorByName(actual.getName());

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
