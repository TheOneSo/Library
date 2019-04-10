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
    public String getAllGenres() {
        StringBuilder builder = new StringBuilder();
        List<Genre> genres = genreRepository.findAll();

        genres.forEach(g -> builder.append(String.format("[Genre_%d]: %s\n", g.getId(), g.getName())));

        return builder.toString();
    }

    @Override
    public String getGenre(long id) {
        Genre genre = genreRepository.findById(id);

        return String.format("[Genre_%d]: %s", genre.getId(), genre.getName());
    }

    @Override
    public void deleteGenre(long id) {
        genreRepository.deleteById(id);
    }
}
