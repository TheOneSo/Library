package com.oneso.library.services;

import com.oneso.library.domain.Genre;
import com.oneso.library.repository.GenreRepository;
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

        genreRepository.insert(genre);
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenre(long id) {
        return genreRepository.findById(id);
    }

    @Override
    public void deleteGenre(long id) {
        genreRepository.deleteById(id);
    }
}
