package com.oneso.library.repository;

import com.oneso.library.domain.Genre;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GenreRepository extends MongoRepository<Genre, String> {

    Optional<Genre> findGenreByName(String name);

    void deleteGenreByName(String name);
}
