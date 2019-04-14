package com.oneso.library.repository;

import com.oneso.library.domain.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {

    @Query("select c from Comment c join fetch c.book where c.book.id = :book_id")
    List<Comment> findCommentByBookId(long book_id);
}
