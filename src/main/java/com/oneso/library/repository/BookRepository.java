package com.oneso.library.repository;

import com.oneso.library.domain.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends MongoRepository<Book, String> {

    Optional<Book> findBookByName(String name);

    @Query("{ 'author.name': :#{#name} }")
    List<Book> findBookByAuthorName(@Param("name") String name);

    @Query("{ 'author.id': :#{#id} }")
    List<Book> findBookByAuthorId(@Param("id") String id);

    @Query("{ 'genre.id': :#{#id} }")
    List<Book> findBookByGenreId(@Param("id") String id);

    @Query("{ 'genre.name': :#{#name} }")
    List<Book> findBookByGenreName(@Param("name") String name);

    void deleteBookByAuthorId(String id);
}
