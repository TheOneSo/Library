package com.oneso.library.repository;

import com.oneso.library.domain.Author;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends PagingAndSortingRepository<Author, Long> {

    List<Author> findAll(Sort sort);

    Optional<Author> findAuthorByName(String name);
}
