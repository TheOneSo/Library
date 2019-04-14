package com.oneso.library.repository;

import com.oneso.library.domain.Genre;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface GenreRepository extends PagingAndSortingRepository<Genre, Long> {

    List<Genre> findAll();

    Optional<Genre> findGenreByName(String name);
}
