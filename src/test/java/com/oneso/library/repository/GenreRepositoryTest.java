package com.oneso.library.repository;

import com.oneso.library.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий для работы с жанрами")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Test
    @DisplayName("добавляет новый жанр")
    void shouldAddNewGenre() {
        long expected = repository.count() + 1;
        repository.insert(new Genre("newGenre"));

        assertThat(repository.count())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("находит жанр по id")
    void shouldFindGenreById() {
        Genre genre = repository.findById(1);

        assertThat(genre.getId())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("находит все жанры")
    void shouldFindAllGenres() {
        List<Genre> genres = repository.findAll();

        assertThat(genres)
                .isNotNull();
    }

    @Test
    @DisplayName("удаляет жанр")
    void shouldDeleteGenre() {
        long expected = repository.count();
        repository.insert(new Genre("delete"));
        repository.deleteById(2);

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
