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

    @Override
    public void addComment(String text, String book_id) {
        Book book = new Book();
        book.setId(book_id);

        Comment comment = new Comment(text, book);
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAllCommentsByBookName(String name) {
        return commentRepository.findCommentByBookName(name);
    }

    @Override
    public void deleteById(String id) {
        commentRepository.deleteById(id);
    }
}
