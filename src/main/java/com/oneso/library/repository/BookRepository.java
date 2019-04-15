package com.oneso.library.repository;

import com.oneso.library.domain.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    Optional<Book> findBookByName(String name);

    @EntityGraph("bookGraph")
    List<Book> findAll();

    @EntityGraph("bookGraph")
    List<Book> findBookByAuthorId(long author_id);

    @EntityGraph("bookGraph")
    List<Book> findBookByGenreId(long genre_id);
}
