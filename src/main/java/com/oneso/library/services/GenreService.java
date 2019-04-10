package com.oneso.library.services;

public interface GenreService {

    void addGenre(String name);

    String getAllGenres();

    String getGenre(long id);

    void deleteGenre(long id);
}
