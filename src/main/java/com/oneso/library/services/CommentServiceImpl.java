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
    public void addComment(String text, long book_id) {
        Book book = new Book();
        book.setId(book_id);

        Comment comment = new Comment(text, book);
        commentRepository.insert(comment);
    }

    @Override
    public String getAllCommentsByBookId(long book_id) {
        StringBuilder builder = new StringBuilder();
        List<Comment> comments = commentRepository.findByBookId(book_id);

        comments.forEach(c -> builder.append(String.format("[%d]: %s\n", c.getId(), c.getText())));

        return builder.toString();
    }

    @Override
    public void deleteById(long id) {
        commentRepository.deleteById(id);
    }
}
