package com.oneso.library.services;

import com.oneso.library.domain.Book;

import java.util.List;

public interface BookService {

    void addBook(String book, String author_id, String genre_id);

    List<Book> getAllBooks();

    Book getBook(String name);

    List<Book> getAllBookByAuthorName(String name);

    List<Book> getAllBookByGenreName(String name);

    void deleteBook(String name);
}
