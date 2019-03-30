package com.oneso.library.dao;

import com.oneso.library.domain.Author;

import java.util.List;

public interface AuthorDao {

    void insert(Author author);

    Author findById(long id);

    Author findByName(String name);

    List<Author> findAll();

    int count();

    void deleteByName(String name);

    void deleteById(long id);
}
