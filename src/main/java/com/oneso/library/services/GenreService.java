package com.oneso.library.services;

import com.oneso.library.domain.Genre;

import java.util.List;

public interface GenreService {

    void addGenre(String name);

    List<Genre> getAllGenres();

    Genre getGenre(long id);

    Genre getGenre(String name);

    void deleteGenre(long id);
}
