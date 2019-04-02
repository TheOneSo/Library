package com.oneso.library.services;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Genre;

public interface BookService {

    void addBook(String book, Author author, Genre genre);

    void showAllBook();

    void showAllBookByAuthor(long authorId);

    void showInfoByBook(String name);

    void deleteBook(long id);
}
