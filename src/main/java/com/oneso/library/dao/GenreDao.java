package com.oneso.library.dao;

import com.oneso.library.domain.Genre;

import java.util.List;

public interface GenreDao {

    void insert(Genre genre);

    Genre findById(long id);

    Genre findByName(String name);

    List<Genre> findAll();

    int count();

    void deleteByName(String name);
}
