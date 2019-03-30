package com.oneso.library.dao;

import com.oneso.library.domain.Book;

import java.util.List;

public interface BookDao {

    void insert(Book book);

    Book findById(long id);

    Book findByName(String name);

    List<Book> findAll();

    int count();

    void deleteByName(String name);
}
