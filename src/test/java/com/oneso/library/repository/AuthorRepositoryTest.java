package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.BootstrapWith;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@SpringBootTest
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с авторами")
class AuthorRepositoryTest {

    @Autowired
    private AuthorRepository repository;

    @Autowired
    private EntityManager em;

    @Test
    @DisplayName("добавляет нового автора")
    void shouldAddNewAuthor() {
        Author author = new Author("test");
        long expected = repository.count() + 1;
        repository.insert(author);

        assertEquals(expected, repository.count());
    }

//    @Test
//    @DisplayName("находит автора по id")
//    void shouldFindAuthorById() {
//        Author author = new Author("testId");
//        author.setId(100);
//        repository.insert(author);
////        em.persist(author);
////        em.flush();
//
//        Author actual = repository.findById(100);
//
//        assertThat(actual.getName())
//                .isEqualTo(author.getName());
//    }
}
