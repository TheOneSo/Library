package com.oneso.library.domain;

import com.oneso.library.repository.BookRepository;
import com.oneso.library.repository.BookRepositoryJpa;
import org.junit.jupiter.api.Disabled;
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
@Import(BookRepositoryJpa.class)
@EntityScan(basePackages = "com.oneso.library.domain")
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Сущность книга")
class BookTest {

    @Autowired
    private BookRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @Disabled
    @DisplayName("маппится корректно")
    void shouldMappedCorrect() {
        Book book = new Book("book", new Author("author"), new Genre("genre"));
        em.persist(book);
        em.flush();

        Book actual = repository.findById(2);

        assertThat(actual.getName())
                .isEqualTo(book.getName());
    }
}
