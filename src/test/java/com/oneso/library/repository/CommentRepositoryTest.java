package com.oneso.library.repository;

import com.oneso.library.domain.Book;
import com.oneso.library.domain.Comment;
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
@Import(CommentRepositoryJpa.class)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с комментариями")
class CommentRepositoryTest {

    @Autowired
    private CommentRepository repository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("добавляет новый комментарий")
    void shouldAddNewComment() {
        Comment comment = new Comment("comment", null);
        repository.insert(comment);

        long comment_id = em.getId(comment, Long.class);

        assertThat(repository.findById(comment_id).getText())
                .isEqualTo(comment.getText());
    }

    @Test
    @DisplayName("находит комментарий по id")
    void shouldFindCommentById() {
        Comment comment = repository.findById(1);

        assertThat(comment.getText())
                .isEqualTo("testC");
    }

    @Test
    @DisplayName("находит комментарии по id книги")
    void shouldFindCommentsByBookId() {
        Comment comment = new Comment("text", new Book(1));
        em.persistAndFlush(comment);

        List<Comment> comments = repository.findByBookId(1);

        assertThat(comments.contains(comment))
                .isTrue();
    }

    @Test
    @DisplayName("удаляет комментарий")
    void shouldDeleteComment() {
        Comment comment = new Comment("delete", new Book(1));
        long expected = repository.count();
        em.persistAndFlush(comment);

        repository.deleteById(em.getId(comment, Long.class));

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
