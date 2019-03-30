package com.oneso.library.services;

import com.oneso.library.domain.Autor;
import com.oneso.library.domain.Genre;

public interface BookService {

    void addBook(String book, Autor autor, Genre genre);

    void showAllBook();

    void showAllInfoToBook(String name);

    void deleteBook(String name);
}
