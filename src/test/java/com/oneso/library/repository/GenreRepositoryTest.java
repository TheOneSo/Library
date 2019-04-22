package com.oneso.library.repository;

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
@DisplayName("Репозиторий для работы с жанрами")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Test
    @DisplayName("добавляет новый жанр")
    void shouldAddNewGenre() {
        Genre genre = new Genre("genre");
        repository.save(genre);

        assertThat(repository.findGenreByName(genre.getName()).get().getName())
                .isEqualTo(genre.getName());
    }

    @Test
    @DisplayName("находит жанр")
    void shouldFindGenre() {
        Genre genre = new Genre("genreId");
        repository.save(genre);

        Optional<Genre> actual = repository.findGenreByName(genre.getName());

        assertThat(actual.get().getName())
                .isEqualTo(genre.getName());
    }

    @Test
    @DisplayName("находит все жанры")
    void shouldFindAllGenres() {
        Genre genre = new Genre("test");
        repository.save(genre);
        List<Genre> genres = repository.findAll();

        assertThat(genres.get(0).getName())
                .isNotNull();
    }

    @Test
    @DisplayName("удаляет жанр")
    void shouldDeleteGenre() {
        Genre genre = new Genre("delete");
        long expected = repository.count();
        repository.save(genre);

        repository.deleteGenreByName("delete");

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
