package com.oneso.library.repository;

import com.oneso.library.domain.Genre;

import java.util.List;

public interface GenreRepository {

    void insert(Genre genre);

    Genre findById(long id);

    List<Genre> findAll();

    long count();

    long deleteById(long id);
}
