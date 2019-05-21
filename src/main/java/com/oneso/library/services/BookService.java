package com.oneso.library.services;

import com.oneso.library.domain.Book;

import java.util.List;

public interface BookService {

    void addBook(String book, String authorId, String genreId);

    List<Book> getAllBooks();

    Book getBook(String id);

    List<Book> getAllBookByAuthorName(String name);

    List<Book> getAllBookByAuthorId(String id);

    List<Book> getAllBookByGenreName(String name);

    List<Book> getAllBookByGenreId(String id);

    void deleteBook(String id);

    void deleteBookByAuthor(String id);
}
