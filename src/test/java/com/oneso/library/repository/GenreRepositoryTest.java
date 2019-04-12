package com.oneso.library.repository;

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
@Import(GenreRepositoryJpa.class)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий для работы с жанрами")
class GenreRepositoryTest {

    @Autowired
    private GenreRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("добавляет новый жанр")
    void shouldAddNewGenre() {
        Genre genre = new Genre("genre");
        repository.insert(genre);

        long genre_id = em.getId(genre, Long.class);

        assertThat(repository.findById(genre_id).getName())
                .isEqualTo(genre.getName());
    }

    @Test
    @DisplayName("находит жанр по id")
    void shouldFindGenreById() {
        Genre genre = new Genre("genreId");
        em.persistAndFlush(genre);

        Genre actual = repository.findById(em.getId(genre, Long.class));

        assertThat(actual.getName())
                .isEqualTo(genre.getName());
    }

    @Test
    @DisplayName("находит все жанры")
    void shouldFindAllGenres() {
        List<Genre> genres = repository.findAll();

        assertThat(genres.get(0).getName())
                .isEqualTo("testG");
    }

    @Test
    @DisplayName("удаляет жанр")
    void shouldDeleteGenre() {
        Genre genre = new Genre("delete");
        long expected = repository.count();
        em.persistAndFlush(genre);

        repository.deleteById(em.getId(genre, Long.class));

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
