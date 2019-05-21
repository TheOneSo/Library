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
        service.addComment("test", "qwe");

        verify(cRepo, times(1)).save(any());
    }

    @Test
    @DisplayName("возвращает список комментариев к книге")
    void shouldReturnListCommentForBook() {
        service.getAllCommentsByBookName("qwe");

        verify(cRepo, times(1)).findCommentByBookName(anyString());
    }

    @Test
    @DisplayName("удаляет комментарий")
    void shouldDeleteComment() {
        service.deleteById("qwe");

        verify(cRepo, times(1)).deleteById(anyString());
    }
}
