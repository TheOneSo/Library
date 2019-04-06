package com.oneso.library.repository;

import com.oneso.library.domain.Author;

import java.util.List;

public interface AuthorRepository {

    void insert(Author author);

    Author findById(long id);

    long count();

    List<Author> findAll();

    long deleteById(long id);
}
