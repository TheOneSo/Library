package com.oneso.library.domain;

import com.oneso.library.repository.GenreRepository;
import com.oneso.library.repository.GenreRepositoryJpa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@Import(GenreRepositoryJpa.class)
@EntityScan(basePackages = "com.oneso.library.domain")
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Сущность жанр")
class GenreTest {

    @Autowired
    private GenreRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("маппится корректно")
    void shouldMappedCorrect() {
        Genre genre = new Genre("genre");
        em.persist(genre);
        em.flush();

        Genre actual = repository.findById(2);

        assertThat(actual.getName())
                .isEqualTo(genre.getName());
    }
}
