package com.oneso.library.services;

import com.oneso.library.domain.Author;

import java.util.List;

public interface AuthorService {

    void addAuthor(String name);

    Author getAuthor(long id);

    List<Author> getAllAuthors();

    void deleteAuthor(long id);
}
