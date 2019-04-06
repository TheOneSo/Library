package com.oneso.library.repository;

import com.oneso.library.domain.Comment;

import java.util.List;

public interface CommentRepository {

    void insert(Comment comment);

    Comment findById(long id);

    List<Comment> findByBookId(long book_id);

    long count();

    long deleteById(long id);
}
