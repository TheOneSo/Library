package com.oneso.library.services;

public interface LibraryService {

    String showAll();

    String showAllBook();

    String showAllAutors();

    String showAllGenres();

    String showBook(String name);

    void createAutor(String name);

    void createBook(String book, String autor, String genre);

    void createGenre(String name);

    void deleteAutor(String autor);

    void deleteBook(String book);

    void deleteGenre(String genre);
}
