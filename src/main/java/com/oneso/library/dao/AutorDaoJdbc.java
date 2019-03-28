package com.oneso.library.dao;

import com.oneso.library.domain.Autor;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@Repository
public class AutorDaoJdbc implements AutorDao {

    private final NamedParameterJdbcOperations jdbc;
    private final JdbcOperations jdbcOperations;

    public AutorDaoJdbc(NamedParameterJdbcOperations jdbc, JdbcOperations jdbcOperations) {
        this.jdbc = jdbc;
        this.jdbcOperations = jdbcOperations;
    }

    private RowMapper<Autor> rowMapper = ((resultSet, i) -> {
       long id = resultSet.getLong("id");
       String name = resultSet.getString("autor");

       return new Autor(id, name);
    });

    @Override
    public void insert(Autor autor) {
        Map<String, Object> param = Collections.singletonMap("name", autor.getName());

        jdbc.update("insert into autors (autor) values (:name)", param);
    }

    @Override
    public Autor findById(long id) {
        Map<String, Object> param = Collections.singletonMap("id", id);

        return jdbc.queryForObject("select * from autors where id = :id", param, rowMapper);
    }

    @Override
    public List<String> findAllBookByAutor(String name) {
        Map<String, Object> param = Collections.singletonMap("name", name);

        return jdbc.query("select b.book from books b " +
                "join autors a on b.id_autor = a.id " +
                "where a.autor = :name", param, (resultSet, i) -> resultSet.getString("book"));
    }

    @Override
    public int count() {
        return jdbcOperations.queryForObject("select count(*) from autors", Integer.class);
    }

    @Override
    public Autor findByName(String name) {
        Map<String, Object> param = Collections.singletonMap("autor", name);

        return jdbc.queryForObject("select * from autors where autor = :autor", param, rowMapper);
    }

    @Override
    public List<Autor> findAll() {
        return jdbc.query("select * from autors", rowMapper);
    }

    @Override
    public void deleteByName(String name) {
        Map<String, Object> param = Collections.singletonMap("autor", name);

        jdbc.update("delete from autors where autor = :autor", param);
    }
}
