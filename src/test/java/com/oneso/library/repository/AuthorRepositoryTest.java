package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataMongoTest
@ComponentScan({"com.oneso.library.repository", "com.oneso.library.events", "com.oneso.library.mongoConfig"})
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с авторами")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private MongoTemplate template;

    @Test
    @DisplayName("добавляет нового автора")
    void shouldAddNewAuthor() {
        Author author = new Author("test", "1");
        repository.save(author);

        assertThat(template.findById("1", Author.class))
                .isNotNull().isEqualToComparingOnlyGivenFields(author, "name");
    }

    @Test
    @DisplayName("находит всех авторов")
    void shouldFindAllAuthorsById() {
        List<Author> authors = repository.findAll();

        assertThat(authors).hasSize(1).allSatisfy(author -> assertThat(author).isNotNull());
    }

    @Test
    @DisplayName("удаляет автора")
    void shouldDeleteAuthor() {
        Author author = new Author("delA", "2");
        long expected = repository.count();
        template.save(author);

        Author actual = template.findById("2", Author.class);

        repository.deleteById(actual.getId());

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
