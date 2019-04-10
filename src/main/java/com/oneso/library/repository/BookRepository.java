package com.oneso.library.repository;

import com.oneso.library.domain.Book;

import java.util.List;

public interface BookRepository {

    void insert(Book book);

    Book findById(long id);

    List<Book> findAll();

    long count();

    List<Book> findAllBookByAuthorId(long author_id);

    List<Book> findAllBookByGenreId(long author_id);

    long deleteById(long id);
}
