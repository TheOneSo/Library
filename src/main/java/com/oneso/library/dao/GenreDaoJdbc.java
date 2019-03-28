package com.oneso.library.dao;

import com.oneso.library.domain.Genre;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class GenreDaoJdbc implements GenreDao {

    private final NamedParameterJdbcOperations jdbc;
    private final JdbcOperations jdbcOperations;

    public GenreDaoJdbc(NamedParameterJdbcOperations jdbc, JdbcOperations jdbcOperations) {
        this.jdbc = jdbc;
        this.jdbcOperations = jdbcOperations;
    }

    private RowMapper<Genre> rowMapper = ((resultSet, i) -> {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("genre");

        return new Genre(id, name);
    });

    @Override
    public void insert(Genre genre) {
        Map<String, Object> param = Collections.singletonMap("genre", genre.getName());

        jdbc.update("insert into genres (genre) values (:genre)", param);
    }

    @Override
    public Genre findById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);

        return jdbc.queryForObject("select * from genres where id = :id", param, rowMapper);
    }

    @Override
    public Genre findByName(String name) {
        Map<String, Object> param = Collections.singletonMap("genre", name);

        return jdbc.queryForObject("select * from genres where genre = :genre", param, rowMapper);
    }

    @Override
    public List<Genre> findAll() {
        return jdbc.query("select * from genres", rowMapper);
    }

    @Override
    public int count() {
        return jdbcOperations.queryForObject("select count(*) from genres", Integer.class);
    }

    @Override
    public void deleteByName(String name) {
        Map<String, Object> param = Collections.singletonMap("genre", name);

        jdbc.update("delete from genres where genre = :genre", param);
    }
}
