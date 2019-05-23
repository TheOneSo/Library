package com.oneso.library.repository;

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
@DisplayName("Репозиторий для работы с жанрами")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Autowired
    private MongoTemplate template;

    @Test
    @DisplayName("добавляет новый жанр")
    void shouldAddNewGenre() {
        Genre genre = new Genre("genre", "123");
        repository.save(genre);

        assertThat(template.findById("123", Genre.class)).isNotNull()
                .isEqualToComparingOnlyGivenFields(genre, "name");
    }

    @Test
    @DisplayName("находит жанр")
    void shouldFindGenre() {
        Optional<Genre> actual = repository.findById("1");

        assertThat(actual.get().getName())
                .isEqualTo("testG");
    }

    @Test
    @DisplayName("находит все жанры")
    void shouldFindAllGenres() {
        List<Genre> genres = repository.findAll();

        assertThat(genres).hasSize(1).allSatisfy(genre -> assertThat(genre).isNotNull());
    }

    @Test
    @DisplayName("удаляет жанр")
    void shouldDeleteGenre() {
        Genre genre = new Genre("delete", "123");
        long expected = repository.count();
        template.save(genre);

        repository.deleteById("123");

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
