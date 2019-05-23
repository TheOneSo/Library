package com.oneso.library.services;

import com.oneso.library.domain.Author;

import java.util.List;

public interface AuthorService {

    void addAuthor(String nameName);

    Author getAuthor(String id);

    List<Author> getAllAuthors();

    void deleteAuthor(String id);
}
