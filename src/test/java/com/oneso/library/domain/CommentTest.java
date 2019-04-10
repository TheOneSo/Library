package com.oneso.library.domain;

import com.oneso.library.repository.CommentRepository;
import com.oneso.library.repository.CommentRepositoryJpa;
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
@Import(CommentRepositoryJpa.class)
@EntityScan(basePackages = "com.oneso.library.domain")
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Сущность комментарий")
class CommentTest {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @Disabled
    @DisplayName("маппится корректно")
    void shouldMappedCorrect() {
        Comment comment = new Comment("comment", new Book("book", null, null));
        em.persist(comment);
        em.flush();

        Comment actual = repository.findById(2);

        assertThat(actual.getText())
                .isEqualTo(comment.getText());

        assertThat(actual.getBook().getName())
                .isEqualTo(comment.getBook().getName());
    }
}
