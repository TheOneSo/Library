package com.oneso.library.services;

import com.oneso.library.dao.GenreDao;
import com.oneso.library.domain.Genre;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public void addGenre(String name) {
        Genre _genre = new Genre(name);

        genreDao.insert(_genre);
    }

    @Override
    public void showAllGenre() {
        List<Genre> genres = genreDao.findAll();

        genres.forEach(genre -> System.out.printf("[Genre]: %s%n", genre.getName()));
    }

    @Override
    public Genre getGenre(String name) {
        return genreDao.findByName(name);
    }

    @Override
    public void deleteGenre(String name) {
        genreDao.deleteByName(name);
    }
}
