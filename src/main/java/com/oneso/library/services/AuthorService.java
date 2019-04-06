package com.oneso.library.services;

public interface AuthorService {

    void addAuthor(String name);

    String getAuthor(long id);

    String getAllAuthors();

    void deleteAuthor(long id);
}
