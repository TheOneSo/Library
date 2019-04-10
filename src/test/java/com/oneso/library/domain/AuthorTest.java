package com.oneso.library.domain;

import com.oneso.library.repository.AuthorRepository;
import com.oneso.library.repository.AuthorRepositoryJpa;
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
@Import(AuthorRepositoryJpa.class)
@EntityScan(basePackages = "com.oneso.library.domain")
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Сущность автор")
class AuthorTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private AuthorRepository repository;

    @Test
    @DisplayName("маппится корректно")
    void shouldMappedCorrect() {
        Author author = new Author("hello");
        em.persist(author);
        em.flush();

        Author actual = repository.findById(2);

        assertThat(actual.getName())
                .isEqualTo(author.getName());
    }
}
