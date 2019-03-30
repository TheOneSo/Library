package com.oneso.library.services;

import com.oneso.library.domain.Author;

public interface AuthorService {

    void addAuthor(String name);

    Author getAuthor(String name);

    Author getAuthor(long id);

    void showAllAuthors();

    void deleteAuthor(long id);
}
