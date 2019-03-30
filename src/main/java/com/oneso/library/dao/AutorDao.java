package com.oneso.library.dao;

import com.oneso.library.domain.Autor;

import java.util.List;

public interface AutorDao {

    void insert(Autor autor);

    Autor findById(long id);

    Autor findByName(String name);

    List<Autor> findAll();

    List<String> findAllBookByAutor(String name);

    int count();

    void deleteByName(String name);
}
