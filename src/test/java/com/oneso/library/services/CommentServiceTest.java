package com.oneso.library.services;

import com.oneso.library.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.mockito.Mockito.*;

@DisplayName("Сервис по работе с комментариями к книгам")
class CommentServiceTest {

    private CommentService service;

    @Mock
    private CommentRepository cRepo;

    @BeforeEach
    void setUp() {
        cRepo = mock(CommentRepository.class);
        service = new CommentServiceImpl(cRepo);
    }

    @Test
    @DisplayName("добавляет новый комментарий к книге")
    void shouldAddNewCommentForBook() {
        service.addComment("test", 1);

        verify(cRepo, times(1)).insert(any());
    }

    @Test
    @DisplayName("показывает все комментарии к книге")
    void shouldShowAllCommentsForBook() {
        service.getAllCommentsByBookId(1);

        verify(cRepo, times(1)).findByBookId(1);
    }

    @Test
    @DisplayName("удаляет комментарий")
    void shouldDeleteComment() {
        service.deleteById(1);

        verify(cRepo, times(1)).deleteById(1);
    }
}
