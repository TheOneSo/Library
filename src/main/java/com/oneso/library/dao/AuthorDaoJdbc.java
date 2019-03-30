package com.oneso.library.dao;

import com.oneso.library.domain.Author;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AuthorDaoJdbc implements AuthorDao {

    private final NamedParameterJdbcOperations jdbc;
    private final JdbcOperations jdbcOperations;

    public AuthorDaoJdbc(NamedParameterJdbcOperations jdbc, JdbcOperations jdbcOperations) {
        this.jdbc = jdbc;
        this.jdbcOperations = jdbcOperations;
    }

    private RowMapper<Author> rowMapper = ((resultSet, i) -> {
       long id = resultSet.getLong("id");
       String name = resultSet.getString("author");

       return new Author(id, name);
    });

    @Override
    public void insert(Author author) {
        Map<String, Object> param = Collections.singletonMap("name", author.getName());

        jdbc.update("insert into authors (author) values (:name)", param);
    }

    @Override
    public Author findById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);

        return jdbc.queryForObject("select * from authors where id = :id", param, rowMapper);
    }

    @Override
    public int count() {
        return jdbcOperations.queryForObject("select count(*) from authors", Integer.class);
    }

    @Override
    public Author findByName(String name) {
        Map<String, Object> param = Collections.singletonMap("author", name);

        return jdbc.queryForObject("select * from authors where author = :author", param, rowMapper);
    }

    @Override
    public List<Author> findAll() {
        return jdbc.query("select * from authors", rowMapper);
    }

    @Override
    public void deleteByName(String name) {
        Map<String, Object> param = Collections.singletonMap("author", name);

        jdbc.update("delete from authors where author = :author", param);
    }

    @Override
    public void deleteById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);

        jdbc.update("delete from authors where id = :id", param);
    }
}
