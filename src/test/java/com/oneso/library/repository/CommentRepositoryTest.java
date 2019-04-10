package com.oneso.library.repository;

import com.oneso.library.domain.Comment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
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

    @Test
    @DisplayName("добавляет новый комментарий")
    void shouldAddNewComment() {
        Comment comment = new Comment("comment", null);
        long expected = repository.count() + 1;
        repository.insert(comment);

        assertThat(repository.count())
                .isEqualTo(expected);
    }

    @Test
    @DisplayName("находит комментарий по id")
    void shouldFindCommentById() {
        Comment comment = repository.findById(1);

        assertThat(comment.getId())
                .isEqualTo(1);
    }

    @Test
    @DisplayName("находит комментарии по id книги")
    void shouldFindCommentsByBookId() {
        List<Comment> comments = repository.findByBookId(1);

        assertThat(comments)
                .isNotNull();
    }

    @Test
    @DisplayName("удаляет комментарий")
    void shouldDeleteComment() {
        long expected = repository.count();
        repository.insert(new Comment("delete", null));
        repository.deleteById(2);

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
