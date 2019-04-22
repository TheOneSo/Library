package com.oneso.library.repository;

import com.oneso.library.domain.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentRepository extends MongoRepository<Comment, String> {

    List<Comment> findCommentByBookName(String name);
}
