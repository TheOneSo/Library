package com.oneso.library.services;

import com.oneso.library.domain.Genre;

public interface GenreService {

    void addGenre(String name);

    void showAllGenre();

    Genre getGenre(String name);

    Genre getGenre(long id);

    void deleteGenre(long id);
}
