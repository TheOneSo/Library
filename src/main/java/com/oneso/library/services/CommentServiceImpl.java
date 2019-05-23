package com.oneso.library.services;

import com.oneso.library.domain.Book;
import com.oneso.library.domain.Comment;
import com.oneso.library.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    // TODO: 2019-04-23 тут
    @Override
    public void addComment(String text, String bookId) {
        Book book = new Book();
        book.setId(bookId);

        Comment comment = new Comment(text, book);
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllCommentsByBookId(String id) {
        return commentRepository.findCommentByBookId(id);
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
