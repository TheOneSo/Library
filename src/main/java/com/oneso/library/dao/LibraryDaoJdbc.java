package com.oneso.library.dao;

import com.oneso.library.domain.Library;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.*;

@SuppressWarnings({"SqlNoDataSourceInspection", "ConstantConditions", "SqlDialectInspection"})
@Repository
public class LibraryDaoJdbc implements LibraryDao {

    private final NamedParameterJdbcOperations jdbc;

    public LibraryDaoJdbc(NamedParameterJdbcOperations jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<Library> rowMapper = ((resultSet, i) -> {
        String autor = resultSet.getString("autor");
        String book = resultSet.getString("book");
        String genre = resultSet.getString("genre");

        return new Library(autor, book, genre);
    });

    @Override
    public List<Library> getAll() {
        return jdbc.query("select\n" +
                "  a.autor, b.book, g.genre\n" +
                "from books b\n" +
                "join autors a on b.id_autor = a.id\n" +
                "join genres g on b.id_genre = g.id", rowMapper);
    }

    @Override
    public List<String> getAllBook() {
        return jdbc.query("select * from books", ((resultSet, i) -> resultSet.getString("book")));
    }

    @Override
    public List<String> getAllAutor() {
        return jdbc.query("select * from autors", ((resultSet, i) -> resultSet.getString("autor")));
    }

    @Override
    public List<String> getAllGenre() {
        return jdbc.query("select * from genres", ((resultSet, i) -> resultSet.getString("genre")));
    }

    @Override
    public List<Library> getByIdAutor(int idAutor) {
        Map<String, Object> param = Collections.singletonMap("id", idAutor);
        return jdbc.query("select\n" +
                "  a.autor, b.book, g.genre\n" +
                "from books b\n" +
                "join autors a on b.id_autor = a.id\n" +
                "join genres g on b.id_genre = g.id\n" +
                "where b.id_autor = :id", param , rowMapper);
    }

    @Override
    public List<Library> getByNameBook(String name) {
        Map<String, Object> param = Collections.singletonMap("name", name);
        return jdbc.query("select\n" +
                "  a.autor, b.book, g.genre\n" +
                "from books b\n" +
                "join autors a on b.id_autor = a.id\n" +
                "join genres g on b.id_genre = g.id\n" +
                "where b.book = :name", param , rowMapper);
    }

    @Override
    public List<Library> getByIdGenre(int idGenre) {
        Map<String, Object> param = Collections.singletonMap("id", idGenre);
        return jdbc.query("select\n" +
                "  a.autor, b.book, g.genre\n" +
                "from books b\n" +
                "join autors a on b.id_autor = a.id\n" +
                "join genres g on b.id_genre = g.id\n" +
                "where b.id_genre = :id", param , rowMapper);
    }

    @Override
    public int findIdAutor(String autor) {
        Map<String, Object> param = Collections.singletonMap("autor", autor);
        try {
            return jdbc.queryForObject("select id from autors where autor = :autor", param, ((resultSet, i) -> resultSet.getInt("id")));
        } catch (EmptyResultDataAccessException ex) {
            return 0;
        }
    }

    @Override
    public int findIdBook(String book) {
        Map<String, Object> param = Collections.singletonMap("book", book);
        try {
            return jdbc.queryForObject("select id from books where book = :book", param, ((resultSet, i) -> resultSet.getInt("id")));
        } catch (EmptyResultDataAccessException ex) {
            return 0;
        }
    }

    @Override
    public int findIdGenre(String genre) {
        Map<String, Object> param = Collections.singletonMap("genre", genre);
        try {
            return jdbc.queryForObject("select id from genres where genre = :genre", param, ((resultSet, i) -> resultSet.getInt("id")));
        } catch (EmptyResultDataAccessException ex) {
            return 0;
        }
    }

    @Override
    public void insertAutor(String autor) {
        Map<String, Object> param = Collections.singletonMap("autor", autor);
        jdbc.update("insert into autors (autor) values (:autor)", param);
    }

    @Override
    public void insertBook(String book, int idAutor, int idGenre) {
        Map<String, Object> param = new HashMap<>();
        param.put("book", book);
        param.put("id_autor", idAutor);
        param.put("id_genre", idGenre);

        jdbc.update("insert into books (book, id_autor, id_genre) values (:book, :id_autor, :id_genre)", param);
    }

    @Override
    public void insertGenre(String genre) {
        Map<String, Object> param = Collections.singletonMap("genre", genre);
        jdbc.update("insert into genres (genre) values (:genre)", param);
    }

    @Override
    public void deleteByIdBook(int id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        jdbc.update("delete from books where id = :id", param);
    }

    @Override
    public void deleteByIdAutor(int id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        jdbc.update("delete from autors where id = :id", param);
    }

    @Override
    public void deleteByIdGenre(int id) {
        Map<String, Object> param = Collections.singletonMap("id", id);
        jdbc.update("delete from genres where id = :id", param);
    }

    @Override
    public void deleteByNameAutor(String name) {
        Map<String, Object> param = Collections.singletonMap("autor", name);
        jdbc.update("delete from autors where autor = :autor", param);
    }

    @Override
    public void deleteByNameBook(String name) {
        Map<String, Object> param = Collections.singletonMap("book", name);
        jdbc.update("delete from books where book = :book", param);
    }

    @Override
    public void deleteByNameGenre(String name) {
        Map<String, Object> param = Collections.singletonMap("genre", name);
        jdbc.update("delete from genres where genre = :genre", param);
    }
}
