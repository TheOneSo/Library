package com.oneso.library.services;

import com.oneso.library.domain.Genre;

import java.util.List;

public interface GenreService {

    void addGenre(String name);

    List<Genre> getAllGenres();

    Genre getGenre(String id);

    void deleteGenre(String id);
}
