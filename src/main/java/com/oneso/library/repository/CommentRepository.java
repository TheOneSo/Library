package com.oneso.library.repository;

import com.oneso.library.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    @EntityGraph("commentGraph")
    List<Comment> findCommentByBookId(long book_id);
}
