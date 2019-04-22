package com.oneso.library.services;

import com.oneso.library.domain.Genre;
import com.oneso.library.repository.GenreRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void addGenre(String name) {
        Genre genre = new Genre(name);

        genreRepository.save(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenre(String name) {
        return genreRepository.findGenreByName(name).orElseGet(Genre::new);
    }

    @Override
    public void deleteGenre(String name) {
        genreRepository.deleteById(name);
    }
}
