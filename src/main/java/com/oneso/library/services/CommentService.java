package com.oneso.library.services;

import com.oneso.library.domain.Comment;

import java.util.List;

public interface CommentService {

    void addComment(String text, String book_id);

    List<Comment> getAllCommentsByBookName(String name);

    void deleteById(String id);
}
