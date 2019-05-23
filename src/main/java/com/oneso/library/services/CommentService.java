package com.oneso.library.services;

import com.oneso.library.domain.Comment;

import java.util.List;

public interface CommentService {

    void addComment(String text, String bookId);

    List<Comment> getAllCommentsByBookId(String id);

    void deleteById(String id);
}
