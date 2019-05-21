package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Comment;
import com.oneso.library.domain.Genre;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.*;

@DataMongoTest
@ComponentScan({"com.oneso.library.repository", "com.oneso.library.events", "com.oneso.library.mongoConfig"})
@DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
@TestPropertySource(properties = {"spring.config.location = classpath:application-test.yml"})
@DisplayName("Репозиторий по работе с комментариями")
class CommentRepositoryTest {

    @Autowired
    private CommentRepository repository;

    @Test
    @DisplayName("добавляет новый комментарий")
    void shouldAddNewComment() {
        Comment comment = new Comment("comment", "123",
                new Book("test", "123", new Author("test", "1"), new Genre("test", "1")));
        repository.save(comment);

        assertThat(repository.findCommentByBookId("123").get(0).getText())
                .isEqualTo(comment.getText());
    }

    @Test
    @DisplayName("удаляет комментарий")
    void shouldDeleteComment() {
        long expected = repository.count() - 1;

        repository.deleteById("1");

        assertThat(repository.count())
                .isEqualTo(expected);
    }
}
