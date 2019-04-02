package com.oneso.library.dao;

import com.oneso.library.domain.Author;
import com.oneso.library.domain.Book;
import com.oneso.library.domain.Genre;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class BookDaoJdbc implements BookDao {

    private final NamedParameterJdbcOperations jdbc;
    private final JdbcOperations jdbcOperations;

    public BookDaoJdbc(NamedParameterJdbcOperations jdbc, JdbcOperations jdbcOperations) {
        this.jdbc = jdbc;
        this.jdbcOperations = jdbcOperations;
    }

    private RowMapper<Book> rowMapper = ((resultSet, i) -> {
       long id = resultSet.getLong("id");
       String name = resultSet.getString("book");

       long id_author = resultSet.getLong("id_author");
       String author = resultSet.getString("author");

       long id_genre = resultSet.getLong("id_genre");
       String genre = resultSet.getString("genre");

       return new Book(id, name, new Author(id_author, author), new Genre(id_genre, genre));
    });

    @Override
    public void insert(Book book) {
        Map<String, Object> param = new HashMap<>();
        param.put("name", book.getName());
        param.put("id_author", book.getAuthor().getId());
        param.put("id_genre", book.getGenre().getId());

        jdbc.update("insert into books (book, id_author, id_genre) values (:name, :id_author, :id_genre)", param);
    }

    @Override
    public Book findById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        return jdbc.queryForObject("select * from books b " +
                "join authors a on b.id_author = a.id " +
                "join genres g on b.id_genre = g.id " +
                "where b.id = :id", param, rowMapper);
    }

    @Override
    public List<Book> findAllBookByAuthorId(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);

        return jdbc.query("select * from books b " +
                "join authors a on b.id_author = a.id " +
                "join genres g on b.id_genre = g.id " +
                "where b.id_author = :id", param, rowMapper);
    }

    @Override
    public Book findByName(String name) {
        Map<String, Object> param = Collections.singletonMap("book", name);
        return jdbc.queryForObject("select * from books b " +
                "join authors a on b.id_author = a.id " +
                "join genres g on b.id_genre = g.id " +
                "where b.book = :book", param, rowMapper);
    }

    @Override
    public List<Book> findAll() {
        return jdbc.query("select * from books b " +
                "join authors a on b.id_author = a.id " +
                "join genres g on b.id_genre = g.id", rowMapper);
    }

    @Override
    public int count() {
        return jdbcOperations.queryForObject("select count(*) from books", Integer.class);
    }

    @Override
    public void deleteByName(String name) {
        Map<String, Object> param = Collections.singletonMap("book", name);
        jdbc.update("delete from books where book = :book", param);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);

        jdbc.update("delete from books where id = :id", param);
    }
}
