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
    List<Book>  findBookByAuthorName(@Param("name") String name);

    @Query("{ 'genre.name': :#{#name} }")
    List<Book> findBookByGenreName(@Param("name") String name);

    void deleteBookByName(String name);

    void deleteBookByAuthorId(String id);

    void deleteBookByAuthorName(String name);
}
