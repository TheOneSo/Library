package com.oneso.library.services;

public interface CommentService {

    void addComment(String text, long book_id);

    String getAllCommentsByBookId(long book_id);

    void deleteById(long id);
}
