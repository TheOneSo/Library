package com.oneso.library.services;

import com.oneso.library.domain.Book;

import java.util.List;

public interface BookService {

    void addBook(String book, long author_id, long genre_id);

    List<Book> getAllBooks();

    Book getBook(long id);

    Book getBook(String name);

    List<Book> getAllBookByAuthorId(long author_id);

    List<Book> getAllBookByGenreId(long genre_id);

    void deleteBook(long id);
}
