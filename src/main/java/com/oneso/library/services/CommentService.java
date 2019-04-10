package com.oneso.library.services;

import com.oneso.library.domain.Comment;

import java.util.List;

public interface CommentService {

    void addComment(String text, long book_id);

    List<Comment> getAllCommentsByBookId(long book_id);

    void deleteById(long id);
}
