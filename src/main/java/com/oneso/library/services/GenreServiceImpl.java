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
        genreRepository.save(new Genre(name));
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getGenre(String id) {
        return genreRepository.findById(id).orElseGet(Genre::new);
    }

    @Override
    public void deleteGenre(String id) {
        genreRepository.deleteById(id);
    }
}
