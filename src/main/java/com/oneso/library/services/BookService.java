package com.oneso.library.services;

public interface BookService {

    void addBook(String book, long author_id, long genre_id);

    String getAllBooks();

    String getBookById(long id);

    String getAllBookByAuthorId(long author_id);

    String getAllBookByGenreId(long genre_id);

    void deleteBook(long id);
}
