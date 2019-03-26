package com.oneso.library.dao;

import com.oneso.library.domain.Library;

import java.util.List;

public interface LibraryDao {

    List<Library> getAll();

    List<String> getAllBook();

    List<String> getAllAutor();

    List<String> getAllGenre();

    List<Library> getByIdAutor(int idAutor);

    List<Library> getByNameBook(String name);

    List<Library> getByIdGenre(int idGenre);

    int findIdAutor(String autor);

    int findIdBook(String book);

    int findIdGenre(String genre);

    void insertAutor(String autor);

    void insertBook(String book, int idAutor, int idGenre);

    void insertGenre(String genre);

    void deleteByIdBook(int id);

    void deleteByIdAutor(int id);

    void deleteByIdGenre(int id);

    void deleteByNameAutor(String name);

    void deleteByNameBook(String name);

    void deleteByNameGenre(String name);
}
